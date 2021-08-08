package com.maplog.b.login.model;

import lombok.Data;

@Data
public class GithubModel {
    private String code;
    private String clientId;
    private String clientSecret;

    public GithubModel(String code,String clientId,String clientSecret){
        this.code=code;
        this.clientId=clientId;
        this.clientSecret=clientSecret;
    }

}
