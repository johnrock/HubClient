package com.jpiser.hubclient.retrofit.github;

import com.jpiser.hubclient.common.logging.LogHelper;
import com.jpiser.hubclient.data.github.GithubApiHelper;
import com.jpiser.hubclient.data.github.model.Profile;

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

    GithubApiAccessor githubApiAccessor;
    LogHelper logHelper;

    public RetrofitGithubApiHelper(LogHelper logHelper) {
        this.logHelper = logHelper;
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
    public void loadProfile(final GithubApiAccessor githubApiAccessor) {
        this.githubApiAccessor = githubApiAccessor;

        RetrofitGithubService retrofitGithubService = createRetrofit().create(RetrofitGithubService.class);

        final Call<Profile> call = retrofitGithubService.userProfile("JakeWharton");

        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                Profile profile = response.body();
                logHelper.debug(LOGTAG, "Retrieved profile: " + profile);

                githubApiAccessor.receiveProfile(profile);
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
