package com.jpiser.hubclient.domain.models;

import com.jpiser.hubclient.data.models.github.GithubOrganization;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John Piser johnpiser@yahoo.com
 */
public class HubOrganizationAdapter {
    public List<HubOrganization> adapt(List<GithubOrganization> githubOrganizations) {

        List<HubOrganization> items = new ArrayList<>();

        if(githubOrganizations != null && githubOrganizations.size() > 0){

            for (GithubOrganization githubOrganization : githubOrganizations) {
                HubOrganization hubOrganization = new HubOrganization();
                hubOrganization.setAvatarUrl(githubOrganization.getAvatarUrl());
                items.add(hubOrganization);
            }

        }

        return items;
    }
}
