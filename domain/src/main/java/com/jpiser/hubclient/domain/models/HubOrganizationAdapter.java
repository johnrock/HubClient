package com.jpiser.hubclient.domain.models;

import com.jpiser.hubclient.data.models.github.Organization;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John Piser johnpiser@yahoo.com
 */
public class HubOrganizationAdapter {
    public List<HubOrganization> adapt(List<Organization> organizations) {

        List<HubOrganization> items = new ArrayList<>();

        if(organizations != null && organizations.size() > 0){

            for (Organization organization : organizations) {
                HubOrganization hubOrganization = new HubOrganization();
                hubOrganization.setAvatarUrl(organization.getAvatarUrl());
                items.add(hubOrganization);
            }

        }

        return items;
    }
}
