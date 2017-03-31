package com.jpiser.hubclient.domain.interactors;

import com.jpiser.hubclient.domain.models.HubIssue;
import com.jpiser.hubclient.domain.models.HubOrganization;
import com.jpiser.hubclient.domain.models.HubRepo;
import com.jpiser.hubclient.domain.models.HubUserProfile;

import java.util.List;

public interface HubInteractor {

    interface HubAccessor  {
        void receiveProfile(HubUserProfile hubUserProfile);
        void receiveOrganziations(List<HubOrganization> organizations);
        void receiveRepos(List<HubRepo> repos);
        void receiveIssues(List<HubIssue> issues);
    }

    void bind(HubAccessor hubAccessor);

    void loadProfile(String UserLogin);

    void loadRepos(String userLogin);

    void loadIssues(String ownerName, String repoName);
}
