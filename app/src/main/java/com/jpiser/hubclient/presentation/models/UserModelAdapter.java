package com.jpiser.hubclient.presentation.models;

import com.jpiser.hubclient.domain.models.HubUser;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class UserModelAdapter {

    public HubUser adapt(UserModel userModel) {
        if(userModel != null){
            HubUser hubUser = new HubUser();
            hubUser.setLogin(userModel.getLogin());
            return hubUser;
        }
        return null;
    }

    public UserModel adapt(HubUser hubUser) {
        if(hubUser != null){
            UserModel userModel = new UserModel();
            userModel.setLogin(hubUser.getLogin());
            return userModel;
        }
        return null;
    }
}
