package com.jpiser.hubclient.data.retrofit;

import com.jpiser.hubclient.data.github.model.Contributor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public interface RetrofitGithubService {

    @GET("repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> repoContributors(@Path("owner") String owner, @Path("repo") String repo);

}