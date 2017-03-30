package com.jpiser.hubclient.presentation.features.profile.model;

import com.jpiser.hubclient.domain.model.HubRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John Piser johnpiser@yahoo.com
 */
public class RepoModelAdapter {
    public List<RepoModel> adapt(List<HubRepo> repos) {

        List<RepoModel> items = new ArrayList<>();

        if(repos != null && repos.size() > 0){
            for (HubRepo repo : repos) {

                RepoModel repoModel = new RepoModel();
                repoModel.setName(repo.getName());
                repoModel.setDescription(repo.getDescription());
                repoModel.setUrl(repo.getUrl());
                items.add(repoModel);
            }
        }
        return items;
    }
}
