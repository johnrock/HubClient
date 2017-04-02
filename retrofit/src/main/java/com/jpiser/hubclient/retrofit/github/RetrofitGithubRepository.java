package com.jpiser.hubclient.retrofit.github;

import com.jpiser.hubclient.data.helpers.LogHelper;
import com.jpiser.hubclient.data.models.github.Issue;
import com.jpiser.hubclient.data.models.github.Organization;
import com.jpiser.hubclient.data.models.github.Profile;
import com.jpiser.hubclient.data.models.github.Repo;
import com.jpiser.hubclient.data.models.shared.Credentials;
import com.jpiser.hubclient.data.repositories.GithubRepository;
import com.jpiser.hubclient.retrofit.RetrofitRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class RetrofitGithubRepository extends RetrofitRepository implements GithubRepository {


    private static final String GITHUB_BASE_URL = "https://api.github.com/";
    private final String LOGTAG = getClass().getSimpleName();

    private RepositoryAccessor repositoryAccessor;

    public RetrofitGithubRepository(LogHelper logHelper) {
        super(logHelper);
    }

    @Override
    public void bind(RepositoryAccessor repositoryAccessor) {
        this.repositoryAccessor = repositoryAccessor;
    }

    @Override
    public void loadProfile(final String userLogin, com.jpiser.hubclient.data.models.shared.Credentials credentials) {

        if(repositoryAccessor == null){
            logHelper.error(LOGTAG, "Error: Must call bind() before calling loadProfile");
            return;
        }

        if(userLogin != null){

            RetrofitGithubAPI retrofitGithubAPI = createRetrofit(credentials, GITHUB_BASE_URL).create(RetrofitGithubAPI.class);

            final Call<Profile> call = retrofitGithubAPI.userProfile(userLogin);

            call.enqueue(new Callback<Profile>() {
                @Override
                public void onResponse(Call<Profile> call, Response<Profile> response) {
                    int responseCode = response.code();

                    repositoryAccessor.receiveProfile(response.body());
                    if(responseCode == 200){
                        loadOrganizations(userLogin);
                    }
                }

                @Override
                public void onFailure(Call<Profile> call, Throwable t) {
                    logHelper.error(LOGTAG, "Error retrieving profile: " + t);
                }
            });
        }
    }

    @Override
    public void loadRepos(String userLogin, com.jpiser.hubclient.data.models.shared.Credentials credentials) {

        if(repositoryAccessor == null){
            logHelper.error(LOGTAG, "Error: Must call bind() before calling loadRepos");
            return;
        }

        if(userLogin != null){

            RetrofitGithubAPI retrofitGithubAPI = createRetrofit(credentials, GITHUB_BASE_URL).create(RetrofitGithubAPI.class);

            Call<List<Repo>> call = retrofitGithubAPI.repos(userLogin);

            call.enqueue(new Callback<List<Repo>>() {
                @Override
                public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                    repositoryAccessor.receiveRepos(response.body());
                }

                @Override
                public void onFailure(Call<List<Repo>> call, Throwable t) {
                    logHelper.error(LOGTAG, "Error retrieving repos: " + t);
                }
            });
        }
    }

    @Override
    public void loadIssues(String ownerName, String repoName, Credentials credentials, String state) {

        if(repositoryAccessor == null){
            logHelper.error(LOGTAG, "Error: Must call bind() before calling loadIssues");
            return;
        }
        if(repoName != null && ownerName != null){

            RetrofitGithubAPI retrofitGithubAPI = createRetrofit(credentials, GITHUB_BASE_URL).create(RetrofitGithubAPI.class);

            Call<List<Issue>> call = retrofitGithubAPI.issues(ownerName, repoName, state);
            call.enqueue(new Callback<List<Issue>>() {
                @Override
                public void onResponse(Call<List<Issue>> call, Response<List<Issue>> response) {
                    repositoryAccessor.receiveIssues(response.body());
                }

                @Override
                public void onFailure(Call<List<Issue>> call, Throwable t) {
                    logHelper.error(LOGTAG, "Error retrieving issues: " + t);
                }
            });
        }

    }

    @Override
    public void createIssue(final String repoName, Issue issue, com.jpiser.hubclient.data.models.shared.Credentials credentials) {
        if(repositoryAccessor == null){
            logHelper.error(LOGTAG, "Error: Must call bind() before calling createIssue");
            return;
        }
        if(issue != null && credentials != null){

            RetrofitGithubAPI retrofitGithubAPI = createRetrofit(credentials, GITHUB_BASE_URL).create(RetrofitGithubAPI.class);


            Call<Issue> call = retrofitGithubAPI.createIssue(credentials.getUsername(), repoName, issue);
            call.enqueue(new Callback<Issue>() {
                @Override
                public void onResponse(Call<Issue> call, Response<Issue> response) {
                    //success = : Status: 201 Created
                    Issue returnedIssue = response.body();
                    repositoryAccessor.receiveIssue(returnedIssue);
                }

                @Override
                public void onFailure(Call<Issue> call, Throwable t) {
                    logHelper.error(LOGTAG, "Error creating issue: " + t);
                }
            });
        }
    }

    @Override
    public void updateIssue(String repoName, Issue issue, com.jpiser.hubclient.data.models.shared.Credentials credentials) {
        if(repositoryAccessor == null){
            logHelper.error(LOGTAG, "Error: Must call bind() before calling updateIssue");
            return;
        }
        if(repoName != null && issue != null && credentials != null){

            RetrofitGithubAPI retrofitGithubAPI = createRetrofit(credentials, GITHUB_BASE_URL).create(RetrofitGithubAPI.class);
            Call<Issue> call = retrofitGithubAPI.updateIssue(credentials.getUsername(), repoName, String.valueOf(issue.getNumber()), issue);

            call.enqueue(new Callback<Issue>() {
                @Override
                public void onResponse(Call<Issue> call, Response<Issue> response) {
                    Issue returnedIssue = response.body();
                    repositoryAccessor.receiveIssue(returnedIssue);
                }

                @Override
                public void onFailure(Call<Issue> call, Throwable t) {
                    logHelper.error(LOGTAG, "Error updating issue: " + t);
                }
            });
        }
    }


    private void loadOrganizations(final String userLogin){

        if(repositoryAccessor == null){
            logHelper.error(LOGTAG, "Error: Must call bind() before calling loadOrganizations");
            return;
        }

        RetrofitGithubAPI retrofitGithubAPI = createRetrofit(null, GITHUB_BASE_URL).create(RetrofitGithubAPI.class);
        final Call<List<Organization>> call = retrofitGithubAPI.organizations(userLogin);
        call.enqueue(new Callback<List<Organization>>() {
            @Override
            public void onResponse(Call<List<Organization>> call, Response<List<Organization>> response) {
                repositoryAccessor.receiveOrganiztions(response.body());
            }

            @Override
            public void onFailure(Call<List<Organization>> call, Throwable t) {
                logHelper.error(LOGTAG, "Error retrieving profile: " + t);
            }
        });
    }
}
