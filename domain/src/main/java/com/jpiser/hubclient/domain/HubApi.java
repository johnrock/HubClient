package com.jpiser.hubclient.domain;

import com.jpiser.hubclient.domain.model.HubUserProfile;

public interface HubApi {

    interface HubAccessor{
        void receiveProfile(HubUserProfile hubUserProfile);
    }


    void loadProfile(HubAccessor hubAccessor);
}
