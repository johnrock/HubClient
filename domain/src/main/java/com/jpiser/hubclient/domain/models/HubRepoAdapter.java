package com.jpiser.hubclient.domain.models;

import com.jpiser.hubclient.data.models.github.GithubRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John Piser johnpiser@yahoo.com
 */
public class HubRepoAdapter {
    public List<HubRepo> adapt(List<GithubRepo> githubRepos) {
        List<HubRepo> items = new ArrayList<>();

        if(githubRepos != null && githubRepos.size() > 0){
            for (GithubRepo githubRepo : githubRepos) {
                HubRepo hubRepo = new HubRepo();
                hubRepo.setName(githubRepo.getName());
                hubRepo.setDescription(githubRepo.getDescription());
                hubRepo.setUrl(githubRepo.getUrl());
                hubRepo.setStargazersCount(githubRepo.getStargazersCount());
                hubRepo.setForksCount(githubRepo.getForksCount());
                hubRepo.setUpdatedAt(githubRepo.getUpdatedAt());
                hubRepo.setLanguage(githubRepo.getLanguage());
                items.add(hubRepo);
            }
        }
        return items;
    }
}
