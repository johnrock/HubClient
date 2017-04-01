package com.jpiser.hubclient.presentation.models;

import com.jpiser.hubclient.domain.models.HubUser;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class IssueUserModelAdapter {

    public HubUser adapt(IssueUserModel issueUserModel) {
        if(issueUserModel != null){
            HubUser hubUser = new HubUser();
            hubUser.setLogin(issueUserModel.getLogin());
            return hubUser;
        }
        return null;
    }

    public IssueUserModel adapt(HubUser hubUser) {
        if(hubUser != null){
            IssueUserModel issueUserModel = new IssueUserModel();
            issueUserModel.setLogin(hubUser.getLogin());
            return issueUserModel;
        }
        return null;
    }
}
