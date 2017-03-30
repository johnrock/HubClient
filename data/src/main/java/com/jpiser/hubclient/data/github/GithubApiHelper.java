package com.jpiser.hubclient.data.github;

import com.jpiser.hubclient.data.github.model.Organization;
import com.jpiser.hubclient.data.github.model.Profile;
import com.jpiser.hubclient.data.github.model.Repo;

import java.util.List;

/**
 * @author John Piser johnpiser@yahoo.com
 */
public interface GithubApiHelper {

    interface GithubApiAccessor{

        void receiveProfile(Profile profile);

        void receiveOrganiztions(List<Organization> organizations);

        void receiveRepos(List<Repo> repos);
    }

    void bind(GithubApiAccessor githubApiAccessor);

    void loadProfile(String userLogin);

    void loadRepos(String userLogin);
}
