package com.jpiser.hubclient.retrofit.github;

import com.jpiser.hubclient.common.logging.LogHelper;
import com.jpiser.hubclient.data.github.GithubApiHelper;
import com.jpiser.hubclient.data.github.model.Organization;
import com.jpiser.hubclient.data.github.model.Profile;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class RetrofitGithubApiHelper implements GithubApiHelper{

    private final String LOGTAG = getClass().getSimpleName();

    LogHelper logHelper;

    private final RetrofitGithubService retrofitGithubService;

    public RetrofitGithubApiHelper(LogHelper logHelper) {
        this.logHelper = logHelper;
        retrofitGithubService = createRetrofit().create(RetrofitGithubService.class);
    }

    private OkHttpClient createHttpClient() {

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();

        if(logHelper.debugMode()){
            //Add as last interceptor
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor);
        }

        return okHttpClientBuilder.build();
    }

    private Retrofit createRetrofit(){

      return     new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .client(createHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    @Override
    public void loadProfile(final GithubApiAccessor githubApiAccessor, final String userLogin) {

        if(userLogin != null){

            final Call<Profile> call = retrofitGithubService.userProfile(userLogin);

            call.enqueue(new Callback<Profile>() {
                @Override
                public void onResponse(Call<Profile> call, Response<Profile> response) {
                    Profile profile = response.body();
                    logHelper.debug(LOGTAG, "Retrieved profile: " + profile);

                    githubApiAccessor.receiveProfile(profile);
                    loadOrganizations(githubApiAccessor, userLogin);
                }

                @Override
                public void onFailure(Call<Profile> call, Throwable t) {
                    logHelper.error(LOGTAG, "Error retrieving profile: " + t);
                    //TODO: Handle failures better. This is just a quick n easy way to handle it.
                    Profile profile = new Profile();
                    profile.setName("Error retreiving profile");
                    githubApiAccessor.receiveProfile(profile);
                }
            });
        }

    }

    private void loadOrganizations(final GithubApiAccessor githubApiAccessor, final String userLogin){

        final Call<List<Organization>> call = retrofitGithubService.organizations(userLogin);
        call.enqueue(new Callback<List<Organization>>() {
            @Override
            public void onResponse(Call<List<Organization>> call, Response<List<Organization>> response) {
                githubApiAccessor.receiveOrganiztions(response.body());
            }

            @Override
            public void onFailure(Call<List<Organization>> call, Throwable t) {
                logHelper.error(LOGTAG, "Error retrieving profile: " + t);
            }
        });

    }
}
