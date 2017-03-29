package com.jpiser.hubclient.data.retrofit;

import com.jpiser.hubclient.data.github.GithubApiHelper;
import com.jpiser.hubclient.data.logging.LogHelper;
import com.jpiser.hubclient.data.github.model.Contributor;

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

        final Call<List<Contributor>> call = retrofitGithubService.repoContributors("square", "retrofit");

        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                for (Contributor contributor : response.body()) {
                    System.out.println("Contributor: " + contributor.toString());
                }
                githubApiAccessor.receiveProfile();
            }
            @Override
            public void onFailure(Call<List<Contributor>> call, Throwable t) {
                System.out.println("Retrofit call failure: " + t.getMessage());
            }
        });
    }
}
