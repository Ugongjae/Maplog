package com.maplog.b.login.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GithubUser {
    private String login;
    private long id;
    private String avatarUrl;
    private String name;

    public GithubUser(String login, long id, String avatarUrl, String name){
        this.login=login;
        this.id=id;
        this.avatarUrl=avatarUrl;
        this.name=name;
    }
}
