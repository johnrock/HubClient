package com.jpiser.hubclient.retrofit.github;

import com.jpiser.hubclient.data.github.model.Contributor;
import com.jpiser.hubclient.data.github.model.Issue;
import com.jpiser.hubclient.data.github.model.Organization;
import com.jpiser.hubclient.data.github.model.Profile;
import com.jpiser.hubclient.data.github.model.Repo;

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

    @GET("users/{userLogin}")
    Call<Profile> userProfile(@Path("userLogin") String userLogin);

    @GET("users/{userLogin}/orgs")
    Call<List<Organization>> organizations(@Path("userLogin") String userLogin);

    @GET("users/{userLogin}/repos")
    Call<List<Repo>> repos(@Path("userLogin") String userLogin);

    @GET("repos/{owner}/{reponame}/issues")
    Call<List<Issue>> issues(@Path("owner") String owner, @Path("reponame") String repoName);

}