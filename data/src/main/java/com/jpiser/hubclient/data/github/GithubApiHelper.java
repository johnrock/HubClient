package com.jpiser.hubclient.data.github;

import com.jpiser.hubclient.data.github.model.Profile;

/**
 * @author John Piser johnpiser@yahoo.com
 */
public interface GithubApiHelper {

    interface GithubApiAccessor{

        void receiveProfile(Profile profile);
    }

    void loadProfile(GithubApiAccessor githubApiAccessor);
}
