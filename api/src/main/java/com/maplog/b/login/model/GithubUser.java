package com.maplog.b.login.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GithubUser {
    private String login;
    private String id;
    private String avatarUrl;

    public GithubUser(String login, String id, String avatarUrl){
        this.login=login;
        this.id=id;
        this.avatarUrl=avatarUrl;
    }
}
