package com.jpiser.hubclient.data.models.github;

/**
 * @author John Piser johnpiser@yahoo.com
 *
 *
 * {
"url": "https://api.github.com/repos/JakeWharton/ActionBarSherlock/issues/1018",
"repository_url": "https://api.github.com/repos/JakeWharton/ActionBarSherlock",
"labels_url": "https://api.github.com/repos/JakeWharton/ActionBarSherlock/issues/1018/labels{/name}",
"comments_url": "https://api.github.com/repos/JakeWharton/ActionBarSherlock/issues/1018/comments",
"events_url": "https://api.github.com/repos/JakeWharton/ActionBarSherlock/issues/1018/events",
"html_url": "https://github.com/JakeWharton/ActionBarSherlock/pull/1018",
"id": 18331401,
"number": 1018,
"title": "Removing unused declared attributes",

"user": {
"login": "jonasfa",
"id": 21069,
"avatar_url": "https://avatars1.githubusercontent.com/u/21069?v=3",
"gravatar_id": "",
"url": "https://api.github.com/users/jonasfa",
"html_url": "https://github.com/jonasfa",
"followers_url": "https://api.github.com/users/jonasfa/followers",
"following_url": "https://api.github.com/users/jonasfa/following{/other_user}",
"gists_url": "https://api.github.com/users/jonasfa/gists{/gist_id}",
"starred_url": "https://api.github.com/users/jonasfa/starred{/owner}{/repo}",
"subscriptions_url": "https://api.github.com/users/jonasfa/subscriptions",
"organizations_url": "https://api.github.com/users/jonasfa/orgs",
"repos_url": "https://api.github.com/users/jonasfa/repos",
"events_url": "https://api.github.com/users/jonasfa/events{/privacy}",
"received_events_url": "https://api.github.com/users/jonasfa/received_events",
"type": "User",
"site_admin": false
},

"labels": [

],
"state": "open",
"locked": false,
"assignee": null,
"assignees": [

],
"milestone": null,
"comments": 0,
"created_at": "2013-08-21T01:22:27Z",
"updated_at": "2014-06-19T08:35:18Z",
"closed_at": null,
"pull_request": {
"url": "https://api.github.com/repos/JakeWharton/ActionBarSherlock/pulls/1018",
"html_url": "https://github.com/JakeWharton/ActionBarSherlock/pull/1018",
"diff_url": "https://github.com/JakeWharton/ActionBarSherlock/pull/1018.diff",
"patch_url": "https://github.com/JakeWharton/ActionBarSherlock/pull/1018.patch"
},
"body": "ABS currently declares ..."
}

 */
public class Issue {

    private String title;
    private String state;
    private User user;
    private int number;
    private int comments;

    public String getTitle() {
        return title;
    }

    public String getState() {
        return state;
    }

    public User getUser() {
        return user;
    }

    public int getNumber() {
        return number;
    }

    public int getComments() {
        return comments;
    }
}
