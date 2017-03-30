package com.jpiser.hubclient.domain.model;

import com.jpiser.hubclient.data.github.model.Repo;
import com.jpiser.hubclient.domain.model.HubRepo;

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
                items.add(hubRepo);
            }
        }
        return items;
    }
}
