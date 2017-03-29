package com.jpiser.hubclient.data.github;

import com.jpiser.hubclient.data.github.model.Organization;
import com.jpiser.hubclient.data.github.model.Profile;

import java.util.List;

/**
 * @author John Piser johnpiser@yahoo.com
 */
public interface GithubApiHelper {

    interface GithubApiAccessor{

        void receiveProfile(Profile profile);
        void receiveOrganiztions(List<Organization> organizations);
    }

    void loadProfile(GithubApiAccessor githubApiAccessor, String userLogin);
}
