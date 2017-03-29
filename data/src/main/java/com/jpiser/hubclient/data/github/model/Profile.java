package com.jpiser.hubclient.data.github.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * @author John Piser johnpiser@yahoo.com
 *
 * {
"login": "defunkt",
"id": 2,
"avatar_url": "https://avatars3.githubusercontent.com/u/2?v=3",
"gravatar_id": "",
"url": "https://api.github.com/users/defunkt",
"html_url": "https://github.com/defunkt",
"followers_url": "https://api.github.com/users/defunkt/followers",
"following_url": "https://api.github.com/users/defunkt/following{/other_user}",
"gists_url": "https://api.github.com/users/defunkt/gists{/gist_id}",
"starred_url": "https://api.github.com/users/defunkt/starred{/owner}{/repo}",
"subscriptions_url": "https://api.github.com/users/defunkt/subscriptions",
"organizations_url": "https://api.github.com/users/defunkt/orgs",
"repos_url": "https://api.github.com/users/defunkt/repos",
"events_url": "https://api.github.com/users/defunkt/events{/privacy}",
"received_events_url": "https://api.github.com/users/defunkt/received_events",
"type": "User",
"site_admin": true,
"name": "Chris Wanstrath",
"company": "@github ",
"blog": "http://chriswanstrath.com/",
"location": "San Francisco",
"email": "chris@github.com",
"hireable": true,
"bio": "🍔 ",
"public_repos": 107,
"public_gists": 273,
"followers": 16078,
"following": 208,
"created_at": "2007-10-20T05:24:19Z",
"updated_at": "2017-03-27T22:02:41Z"
}
v
 */

public class Profile {

    private String login;
    private int id;

    @SerializedName("avatar_url")
    private String avatarUrl;

    private String url;

    @SerializedName("html_url")
    private String htmlUrl;

    @SerializedName("repos_url")
    private String reposUrl;

    @SerializedName("organizations_url")
    private String organizationsUrl;

    private String name;

    @SerializedName("site_admin")
    private boolean siteAdmin;

    private String company;
    private String blog;
    private String location;
    private String email;
    private String bio;

    @SerializedName("public_repos")
    private int publicRepos;

    @SerializedName("public_gists")
    private int publicGists;

    private int followers;
    private int following;

    @SerializedName("created_at")
    private Date createdAt;

    @SerializedName("updated_at")
    private Date updatedAt;


    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getUrl() {
        return url;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getReposUrl() {
        return reposUrl;
    }

    public String getOrganizationsUrl() {
        return organizationsUrl;
    }

    public String getName() {
        return name;
    }

    public boolean isSiteAdmin() {
        return siteAdmin;
    }

    public String getCompany() {
        return company;
    }

    public String getBlog() {
        return blog;
    }

    public String getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public String getBio() {
        return bio;
    }

    public int getPublicRepos() {
        return publicRepos;
    }

    public int getPublicGists() {
        return publicGists;
    }

    public int getFollowers() {
        return followers;
    }

    public int getFollowing() {
        return following;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "name='" + name + '\'' +
                ", login='" + login + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }
}
