package com.jpiser.hubclient.domain.model;

import com.jpiser.hubclient.data.github.model.Organization;

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
