package com.jpiser.hubclient.retrofit.github;

import com.jpiser.hubclient.data.models.github.GithubContributor;
import com.jpiser.hubclient.data.models.github.GithubIssue;
import com.jpiser.hubclient.data.models.github.GithubOrganization;
import com.jpiser.hubclient.data.models.github.GithubProfile;
import com.jpiser.hubclient.data.models.github.GithubRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public interface RetrofitGithubAPI {

    @GET("repos/{owner}/{repo}/contributors")
    Call<List<GithubContributor>> repoContributors(@Path("owner") String owner, @Path("repo") String repo);

    @GET("users/{userLogin}")
    Call<GithubProfile> userProfile(@Path("userLogin") String userLogin);

    @GET("users/{userLogin}/orgs")
    Call<List<GithubOrganization>> organizations(@Path("userLogin") String userLogin);

    @GET("users/{userLogin}/repos")
    Call<List<GithubRepo>> repos(@Path("userLogin") String userLogin);

    @GET("repos/{owner}/{reponame}/issues")
    Call<List<GithubIssue>> issues(@Path("owner") String owner, @Path("reponame") String repoName, @Query("state") String state);

    @POST("repos/{owner}/{reponame}/issues")
    Call<GithubIssue> createIssue(@Path("owner") String owner, @Path("reponame") String repoName, @Body GithubIssue githubIssue);

    @PATCH("repos/{owner}/{reponame}/issues/{number}")
    Call<GithubIssue> updateIssue(@Path("owner") String owner, @Path("reponame") String repoName, @Path("number") String number, @Body GithubIssue githubIssue);

}