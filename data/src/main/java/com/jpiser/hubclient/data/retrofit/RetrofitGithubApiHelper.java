package com.jpiser.hubclient.data.retrofit;

import com.jpiser.hubclient.data.github.GithubApiHelper;
import com.jpiser.hubclient.data.retrofit.model.Contributor;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class RetrofitGithubApiHelper implements GithubApiHelper{


    private GithubApiAccessor githubApiAccessor;

    @Override
    public void loadProfile(final GithubApiAccessor githubApiAccessor) {
        this.githubApiAccessor = githubApiAccessor;

        RetrofitGithubService retrofitGithubService = RetrofitGithubService.retrofit.create(RetrofitGithubService.class);

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
