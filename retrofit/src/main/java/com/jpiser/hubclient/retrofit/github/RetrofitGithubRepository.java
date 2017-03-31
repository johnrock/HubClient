package com.jpiser.hubclient.retrofit.github;

import com.jpiser.hubclient.common.logging.LogHelper;
import com.jpiser.hubclient.data.models.github.Issue;
import com.jpiser.hubclient.data.models.github.Organization;
import com.jpiser.hubclient.data.models.github.Profile;
import com.jpiser.hubclient.data.models.github.Repo;
import com.jpiser.hubclient.data.repositories.GithubRepository;

import java.io.IOException;
import java.util.List;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class RetrofitGithubRepository implements GithubRepository {

    public static final String AUTHORIZATION = "Authorization";

    private final String LOGTAG = getClass().getSimpleName();

    LogHelper logHelper;

    private RepositoryAccessor repositoryAccessor;

    public RetrofitGithubRepository(LogHelper logHelper) {
        this.logHelper = logHelper;
    }

    @Override
    public void bind(RepositoryAccessor repositoryAccessor) {
        this.repositoryAccessor = repositoryAccessor;
    }

    private OkHttpClient createHttpClient(final com.jpiser.hubclient.data.models.shared.Credentials credentials) {

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();

        if(credentials != null && !credentials.readOnly()){

            okHttpClientBuilder.addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder()
                            .header(AUTHORIZATION, Credentials.basic(credentials.getUsername(), credentials.getPassword()))
                            .method(original.method(), original.body());
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }

        if(logHelper.debugMode()){
            //Add as last interceptor
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor);
        }

        return okHttpClientBuilder.build();
    }


    private Retrofit createRetrofit(com.jpiser.hubclient.data.models.shared.Credentials credentials){

      return     new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .client(createHttpClient(credentials))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void loadProfile(final String userLogin, com.jpiser.hubclient.data.models.shared.Credentials credentials) {

        if(repositoryAccessor == null){
            logHelper.error(LOGTAG, "Error: Must call bind() before calling loadProfile");
            return;
        }

        if(userLogin != null){

            RetrofitGithubService retrofitGithubService = createRetrofit(credentials).create(RetrofitGithubService.class);

            final Call<Profile> call = retrofitGithubService.userProfile(userLogin);

            call.enqueue(new Callback<Profile>() {
                @Override
                public void onResponse(Call<Profile> call, Response<Profile> response) {
                    int responseCode = response.code();
                    Profile profile = response.body();

                    repositoryAccessor.receiveProfile(profile);
                    loadOrganizations(userLogin);
                }

                @Override
                public void onFailure(Call<Profile> call, Throwable t) {
                    logHelper.error(LOGTAG, "Error retrieving profile: " + t);
                    //TODO: Handle failures better.
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

            RetrofitGithubService retrofitGithubService = createRetrofit(credentials).create(RetrofitGithubService.class);

            Call<List<Repo>> call = retrofitGithubService.repos(userLogin);

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
    public void loadIssues(String ownerName, String repoName, com.jpiser.hubclient.data.models.shared.Credentials credentials) {

        if(repositoryAccessor == null){
            logHelper.error(LOGTAG, "Error: Must call bind() before calling loadIssues");
            return;
        }
        if(repoName != null && ownerName != null){

            RetrofitGithubService retrofitGithubService = createRetrofit(credentials).create(RetrofitGithubService.class);

            Call<List<Issue>> call = retrofitGithubService.issues(ownerName, repoName);
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
    public void createIssue(final String repoName, String title, String body, com.jpiser.hubclient.data.models.shared.Credentials credentials) {
        if(repositoryAccessor == null){
            logHelper.error(LOGTAG, "Error: Must call bind() before calling createIssue");
            return;
        }
        if(title != null && credentials != null){

            RetrofitGithubService retrofitGithubService = createRetrofit(credentials).create(RetrofitGithubService.class);
            Issue issue = new Issue();
            issue.setTitle(title);
            issue.setBody(body);

            Call<Issue> call = retrofitGithubService.createIssue(credentials.getUsername(), repoName, issue);
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


    private void loadOrganizations(final String userLogin){

        if(repositoryAccessor == null){
            logHelper.error(LOGTAG, "Error: Must call bind() before calling loadOrganizations");
            return;
        }

        RetrofitGithubService retrofitGithubService = createRetrofit(null).create(RetrofitGithubService.class);
        final Call<List<Organization>> call = retrofitGithubService.organizations(userLogin);
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
