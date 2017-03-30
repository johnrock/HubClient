package com.jpiser.hubclient.presentation.features.issues.model;

import com.jpiser.hubclient.domain.model.HubUser;

/**
 * @author John Piser johnpiser@yahoo.com
 */

class IssueUserModelAdapter {
    public IssueUserModel adapt(HubUser hubUser) {
        if(hubUser != null){
            IssueUserModel issueUserModel = new IssueUserModel();
            issueUserModel.setLogin(hubUser.getLogin());
            return issueUserModel;
        }
        return null;
    }
}
