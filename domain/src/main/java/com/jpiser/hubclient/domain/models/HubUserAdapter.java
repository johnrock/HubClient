package com.jpiser.hubclient.domain.models;

import com.jpiser.hubclient.data.models.github.User;

/**
 * @author John Piser johnpiser@yahoo.com
 */

class HubUserAdapter {
    public HubUser adapt(User user) {
        if(user != null){
            HubUser hubUser = new HubUser();
            hubUser.setLogin(user.getLogin());
            return hubUser;
        }
        return null;
    }
}
