package com.jpiser.hubclient.presentation.models;

import java.util.Date;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class UserProfileModel {
    private String login;
    private int id;
    private String avatarUrl;
    private String url;
    private String htmlUrl;
    private String reposUrl;
    private String organizationsUrl;
    private String name;
    private boolean siteAdmin;
    private String company;
    private String blog;
    private String location;
    private String email;
    private String bio;
    private int publicRepos;
    private int publicGists;
    private int followers;
    private int following;
    private Date createdAt;
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

    public void setLogin(String login) {
        this.login = login;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public void setReposUrl(String reposUrl) {
        this.reposUrl = reposUrl;
    }

    public void setOrganizationsUrl(String organizationsUrl) {
        this.organizationsUrl = organizationsUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSiteAdmin(boolean siteAdmin) {
        this.siteAdmin = siteAdmin;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setPublicRepos(int publicRepos) {
        this.publicRepos = publicRepos;
    }

    public void setPublicGists(int publicGists) {
        this.publicGists = publicGists;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
