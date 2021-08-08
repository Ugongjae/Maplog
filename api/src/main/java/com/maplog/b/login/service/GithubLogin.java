package com.maplog.b.login.service;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class GithubLogin {

    @Value("${oauth.github.client-id}")
    private String clientId;

    @Value("${oauth.github.client-secret}")
    private String clientSecret;


    public void getAccessToken(String code){
        String address = "https://github.com/login/oauth/access_token";

        RestTemplate rt = new RestTemplate();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("code",code);
        jsonObject.addProperty("client_id",clientId);
        jsonObject.addProperty("client_secret",clientSecret);

        Map<String,String> map=new HashMap<>();
        map.put("code",code);
        map.put("client_id",clientId);
        map.put("client_secret",clientSecret);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String,String>> httpEntity = new HttpEntity<>(map,httpHeaders);

        ResponseEntity<String> responseEntity = rt.postForEntity(address,httpEntity,String.class);



        System.out.println(responseEntity.getBody());
    }
}
