package com.jpiser.hubclient.data.github;

/**
 * @author John Piser johnpiser@yahoo.com
 */
public interface GithubApiHelper {

    interface GithubApiAccessor{

        void receiveProfile();
    }

    void loadProfile(GithubApiAccessor githubApiAccessor);
}
