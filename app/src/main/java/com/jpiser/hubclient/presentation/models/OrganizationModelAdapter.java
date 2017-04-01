package com.jpiser.hubclient.presentation.models;

import com.jpiser.hubclient.domain.models.HubOrganization;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John Piser johnpiser@yahoo.com
 */
public class OrganizationModelAdapter {
    public List<OrganizationModel> adapt(List<HubOrganization> organizations) {
        List<OrganizationModel> items = new ArrayList<>();

        if(organizations != null && organizations.size() > 0){
            for (HubOrganization organization : organizations) {

                OrganizationModel organizationModel = new OrganizationModel();
                organizationModel.setAvatarUrl(organization.getAvatarUrl());
                items.add(organizationModel);
            }

        }

        return items;
    }
}
