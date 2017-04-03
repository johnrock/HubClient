package com.jpiser.hubclient.retrofit.github;

import com.jpiser.hubclient.data.helpers.LogHelper;
import com.jpiser.hubclient.data.models.github.GithubIssue;
import com.jpiser.hubclient.data.models.github.GithubOrganization;
import com.jpiser.hubclient.data.models.github.GithubProfile;
import com.jpiser.hubclient.data.models.github.GithubRepo;
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

            final Call<GithubProfile> call = retrofitGithubAPI.userProfile(userLogin);

            call.enqueue(new Callback<GithubProfile>() {
                @Override
                public void onResponse(Call<GithubProfile> call, Response<GithubProfile> response) {
                    int responseCode = response.code();
                    repositoryAccessor.receiveProfile(response.body());
                }

                @Override
                public void onFailure(Call<GithubProfile> call, Throwable t) {
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

            Call<List<GithubRepo>> call = retrofitGithubAPI.repos(userLogin);

            call.enqueue(new Callback<List<GithubRepo>>() {
                @Override
                public void onResponse(Call<List<GithubRepo>> call, Response<List<GithubRepo>> response) {
                    repositoryAccessor.receiveRepos(response.body());
                }

                @Override
                public void onFailure(Call<List<GithubRepo>> call, Throwable t) {
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

            Call<List<GithubIssue>> call = retrofitGithubAPI.issues(ownerName, repoName, state);
            call.enqueue(new Callback<List<GithubIssue>>() {
                @Override
                public void onResponse(Call<List<GithubIssue>> call, Response<List<GithubIssue>> response) {
                    repositoryAccessor.receiveIssues(response.body());
                }

                @Override
                public void onFailure(Call<List<GithubIssue>> call, Throwable t) {
                    logHelper.error(LOGTAG, "Error retrieving issues: " + t);
                }
            });
        }

    }

    @Override
    public void createIssue(final String repoName, GithubIssue githubIssue, com.jpiser.hubclient.data.models.shared.Credentials credentials) {
        if(repositoryAccessor == null){
            logHelper.error(LOGTAG, "Error: Must call bind() before calling createIssue");
            return;
        }
        if(githubIssue != null && credentials != null){

            RetrofitGithubAPI retrofitGithubAPI = createRetrofit(credentials, GITHUB_BASE_URL).create(RetrofitGithubAPI.class);


            Call<GithubIssue> call = retrofitGithubAPI.createIssue(credentials.getUsername(), repoName, githubIssue);
            call.enqueue(new Callback<GithubIssue>() {
                @Override
                public void onResponse(Call<GithubIssue> call, Response<GithubIssue> response) {
                    //success = : Status: 201 Created
                    GithubIssue returnedGithubIssue = response.body();
                    repositoryAccessor.receiveIssue(returnedGithubIssue);
                }

                @Override
                public void onFailure(Call<GithubIssue> call, Throwable t) {
                    logHelper.error(LOGTAG, "Error creating githubIssue: " + t);
                }
            });
        }
    }

    @Override
    public void updateIssue(String repoName, GithubIssue githubIssue, com.jpiser.hubclient.data.models.shared.Credentials credentials) {
        if(repositoryAccessor == null){
            logHelper.error(LOGTAG, "Error: Must call bind() before calling updateIssue");
            return;
        }
        if(repoName != null && githubIssue != null && credentials != null){

            RetrofitGithubAPI retrofitGithubAPI = createRetrofit(credentials, GITHUB_BASE_URL).create(RetrofitGithubAPI.class);
            Call<GithubIssue> call = retrofitGithubAPI.updateIssue(credentials.getUsername(), repoName, String.valueOf(githubIssue.getNumber()), githubIssue);

            call.enqueue(new Callback<GithubIssue>() {
                @Override
                public void onResponse(Call<GithubIssue> call, Response<GithubIssue> response) {
                    GithubIssue returnedGithubIssue = response.body();
                    repositoryAccessor.receiveIssue(returnedGithubIssue);
                }

                @Override
                public void onFailure(Call<GithubIssue> call, Throwable t) {
                    logHelper.error(LOGTAG, "Error updating githubIssue: " + t);
                }
            });
        }
    }

    @Override
    public void loadOrganizations(final String userLogin){

        if(repositoryAccessor == null){
            logHelper.error(LOGTAG, "Error: Must call bind() before calling loadOrganizations");
            return;
        }

        RetrofitGithubAPI retrofitGithubAPI = createRetrofit(null, GITHUB_BASE_URL).create(RetrofitGithubAPI.class);
        final Call<List<GithubOrganization>> call = retrofitGithubAPI.organizations(userLogin);
        call.enqueue(new Callback<List<GithubOrganization>>() {
            @Override
            public void onResponse(Call<List<GithubOrganization>> call, Response<List<GithubOrganization>> response) {
                repositoryAccessor.receiveOrganiztions(response.body());
            }

            @Override
            public void onFailure(Call<List<GithubOrganization>> call, Throwable t) {
                logHelper.error(LOGTAG, "Error retrieving profile: " + t);
            }
        });
    }
}
