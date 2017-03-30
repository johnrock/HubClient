package com.jpiser.hubclient.domain;

import com.jpiser.hubclient.domain.model.HubOrganization;
import com.jpiser.hubclient.domain.model.HubRepo;
import com.jpiser.hubclient.domain.model.HubUserProfile;

import java.util.List;

public interface HubApi {

    interface HubAccessor{

        void receiveProfile(HubUserProfile hubUserProfile);
        void receiveOrganziations(List<HubOrganization> organizations);
        void receiveRepos(List<HubRepo> repos);
    }

    void bind(HubAccessor hubAccessor);

    void loadProfile(String UserLogin);

    void loadRepos(String userLogin);
}
