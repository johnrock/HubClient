package com.jpiser.hubclient.presentation.models;

import com.jpiser.hubclient.domain.models.HubRepo;

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
                repoModel.setLanguage(repo.getLanguage());
                repoModel.setUpdatedAt(repo.getUpdatedAt());
                repoModel.setStargazersCount(repo.getStargazersCount());
                repoModel.setForksCount(repo.getForksCount());
                items.add(repoModel);
            }
        }
        return items;
    }
}
