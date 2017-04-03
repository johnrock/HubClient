package com.jpiser.hubclient.domain.models;

import com.jpiser.hubclient.data.models.github.GithubUser;

/**
 * @author John Piser johnpiser@yahoo.com
 */

class HubUserAdapter {

    public GithubUser adapt(HubUser hubUser) {
        if(hubUser != null){
            GithubUser githubUser = new GithubUser();
            githubUser.setLogin(hubUser.getLogin());
            return githubUser;
        }
        return null;
    }
    public HubUser adapt(GithubUser githubUser) {
        if(githubUser != null){
            HubUser hubUser = new HubUser();
            hubUser.setLogin(githubUser.getLogin());
            return hubUser;
        }
        return null;
    }
}
