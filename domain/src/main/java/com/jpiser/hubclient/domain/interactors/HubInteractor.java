package com.jpiser.hubclient.domain.interactors;

import com.jpiser.hubclient.data.models.shared.Credentials;
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
        void receiveIssue(HubIssue hubIssue);
    }
    void bind(HubAccessor hubAccessor);

    String getName();

    void loadProfile(String UserLogin, Credentials credentials);

    void loadRepos(String userLogin, Credentials credentials);

    void loadIssues(String ownerName, String repoName, Credentials credentials);

    void createIssue(String repoName, HubIssue hubIssue, Credentials credentials);

    void updateIssue(String repoName, HubIssue hubIssue, Credentials credentials);
}
