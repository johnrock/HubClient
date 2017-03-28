package com.jpiser.hubclient.data.retrofit;

import com.jpiser.hubclient.data.retrofit.model.Contributor;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public interface RetrofitGithubService {

     Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    @GET("repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> repoContributors(@Path("owner") String owner, @Path("repo") String repo);

}