package com.jpiser.hubclient.domain;

import com.jpiser.hubclient.domain.model.HubProfile;

public interface HubApi {

    interface HubAccessor{
        void receiveProfile(HubProfile hubProfile);
    }


    void loadProfile(HubAccessor hubAccessor);
}
