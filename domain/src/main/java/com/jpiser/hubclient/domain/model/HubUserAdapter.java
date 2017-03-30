package com.jpiser.hubclient.domain.model;

import com.jpiser.hubclient.data.github.model.User;

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
