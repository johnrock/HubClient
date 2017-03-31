package com.jpiser.hubclient.domain.models;

import com.jpiser.hubclient.data.models.github.Repo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John Piser johnpiser@yahoo.com
 */
public class HubRepoAdapter {
    public List<HubRepo> adapt(List<Repo> repos) {
        List<HubRepo> items = new ArrayList<>();

        if(repos != null && repos.size() > 0){
            for (Repo repo : repos) {
                HubRepo hubRepo = new HubRepo();
                hubRepo.setName(repo.getName());
                hubRepo.setDescription(repo.getDescription());
                hubRepo.setUrl(repo.getUrl());
                hubRepo.setStargazersCount(repo.getStargazersCount());
                hubRepo.setForksCount(repo.getForksCount());
                hubRepo.setUpdatedAt(repo.getUpdatedAt());
                hubRepo.setLanguage(repo.getLanguage());
                items.add(hubRepo);
            }
        }
        return items;
    }
}
