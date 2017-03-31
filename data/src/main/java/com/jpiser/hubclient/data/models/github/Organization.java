package com.jpiser.hubclient.data.models.github;

import com.google.gson.annotations.SerializedName;

/**
 * @author John Piser johnpiser@yahoo.com
 *
 * {
"login": "square",
"id": 82592,
"url": "https://api.github.com/orgs/square",
"repos_url": "https://api.github.com/orgs/square/repos",
"events_url": "https://api.github.com/orgs/square/events",
"hooks_url": "https://api.github.com/orgs/square/hooks",
"issues_url": "https://api.github.com/orgs/square/issues",
"members_url": "https://api.github.com/orgs/square/members{/member}",
"public_members_url": "https://api.github.com/orgs/square/public_members{/member}",
"avatar_url": "https://avatars3.githubusercontent.com/u/82592?v=3",
"description": ""
}
 */

public class Organization {

    @SerializedName("avatar_url")
    private String avatarUrl;


    public String getAvatarUrl() {
        return avatarUrl;
    }
}
