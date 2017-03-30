package com.jpiser.hubclient.data.github.model;

/**
 * @author John Piser johnpiser@yahoo.com
 *
 * {
"id": 5211331,
"name": "faceup",
"full_name": "defunkt/faceup",
"owner": {
"login": "defunkt",
"id": 2,
"avatar_url": "https://avatars3.githubusercontent.com/u/2?v=3",
"gravatar_id": "",
"url": "https://api.github.com/users/defunkt",
"html_url": "https://github.com/defunkt",
"followers_url": "https://api.github.com/users/defunkt/followers",
"following_url": "https://api.github.com/users/defunkt/following{/other_user}",
"gists_url": "https://api.github.com/users/defunkt/gists{/gist_id}",
"starred_url": "https://api.github.com/users/defunkt/starred{/owner}{/repo}",
"subscriptions_url": "https://api.github.com/users/defunkt/subscriptions",
"organizations_url": "https://api.github.com/users/defunkt/orgs",
"repos_url": "https://api.github.com/users/defunkt/repos",
"events_url": "https://api.github.com/users/defunkt/events{/privacy}",
"received_events_url": "https://api.github.com/users/defunkt/received_events",
"type": "User",
"site_admin": true
},
"private": false,
"html_url": "https://github.com/defunkt/faceup",
"description": "More than just mustaches.",
"fork": true,
"url": "https://api.github.com/repos/defunkt/faceup",
"forks_url": "https://api.github.com/repos/defunkt/faceup/forks",
"keys_url": "https://api.github.com/repos/defunkt/faceup/keys{/key_id}",
"collaborators_url": "https://api.github.com/repos/defunkt/faceup/collaborators{/collaborator}",
"teams_url": "https://api.github.com/repos/defunkt/faceup/teams",
"hooks_url": "https://api.github.com/repos/defunkt/faceup/hooks",
"issue_events_url": "https://api.github.com/repos/defunkt/faceup/issues/events{/number}",
"events_url": "https://api.github.com/repos/defunkt/faceup/events",
"assignees_url": "https://api.github.com/repos/defunkt/faceup/assignees{/user}",
"branches_url": "https://api.github.com/repos/defunkt/faceup/branches{/branch}",
"tags_url": "https://api.github.com/repos/defunkt/faceup/tags",
"blobs_url": "https://api.github.com/repos/defunkt/faceup/git/blobs{/sha}",
"git_tags_url": "https://api.github.com/repos/defunkt/faceup/git/tags{/sha}",
"git_refs_url": "https://api.github.com/repos/defunkt/faceup/git/refs{/sha}",
"trees_url": "https://api.github.com/repos/defunkt/faceup/git/trees{/sha}",
"statuses_url": "https://api.github.com/repos/defunkt/faceup/statuses/{sha}",
"languages_url": "https://api.github.com/repos/defunkt/faceup/languages",
"stargazers_url": "https://api.github.com/repos/defunkt/faceup/stargazers",
"contributors_url": "https://api.github.com/repos/defunkt/faceup/contributors",
"subscribers_url": "https://api.github.com/repos/defunkt/faceup/subscribers",
"subscription_url": "https://api.github.com/repos/defunkt/faceup/subscription",
"commits_url": "https://api.github.com/repos/defunkt/faceup/commits{/sha}",
"git_commits_url": "https://api.github.com/repos/defunkt/faceup/git/commits{/sha}",
"comments_url": "https://api.github.com/repos/defunkt/faceup/comments{/number}",
"issue_comment_url": "https://api.github.com/repos/defunkt/faceup/issues/comments{/number}",
"contents_url": "https://api.github.com/repos/defunkt/faceup/contents/{+path}",
"compare_url": "https://api.github.com/repos/defunkt/faceup/compare/{base}...{head}",
"merges_url": "https://api.github.com/repos/defunkt/faceup/merges",
"archive_url": "https://api.github.com/repos/defunkt/faceup/{archive_format}{/ref}",
"downloads_url": "https://api.github.com/repos/defunkt/faceup/downloads",
"issues_url": "https://api.github.com/repos/defunkt/faceup/issues{/number}",
"pulls_url": "https://api.github.com/repos/defunkt/faceup/pulls{/number}",
"milestones_url": "https://api.github.com/repos/defunkt/faceup/milestones{/number}",
"notifications_url": "https://api.github.com/repos/defunkt/faceup/notifications{?since,all,participating}",
"labels_url": "https://api.github.com/repos/defunkt/faceup/labels{/name}",
"releases_url": "https://api.github.com/repos/defunkt/faceup/releases{/id}",
"deployments_url": "https://api.github.com/repos/defunkt/faceup/deployments",
"created_at": "2012-07-28T02:11:56Z",
"updated_at": "2016-10-23T19:22:03Z",
"pushed_at": "2012-07-28T02:40:26Z",
"git_url": "git://github.com/defunkt/faceup.git",
"ssh_url": "git@github.com:defunkt/faceup.git",
"clone_url": "https://github.com/defunkt/faceup.git",
"svn_url": "https://github.com/defunkt/faceup",
"homepage": "http://faceup.me/",
"size": 1994,
"stargazers_count": 6,
"watchers_count": 6,
"language": "JavaScript",
"has_issues": false,
"has_projects": true,
"has_downloads": true,
"has_wiki": true,
"has_pages": false,
"forks_count": 6,
"mirror_url": null,
"open_issues_count": 1,
"forks": 6,
"open_issues": 1,
"watchers": 6,
"default_branch": "master"
},

 */

public class Repo {

    private String name;
    private String description;
    private String url;



    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Repo{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
