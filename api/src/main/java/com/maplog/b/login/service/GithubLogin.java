package com.maplog.b.login.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.maplog.b.login.model.GithubToken;
import com.maplog.b.login.model.GithubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class GithubLogin {

    @Autowired
    private GithubUserDao githubUserDao;

    @Value("${oauth.github.client-id}")
    private String clientId;

    @Value("${oauth.github.client-secret}")
    private String clientSecret;

    public void getAccessToken(String code){
        String address = "https://github.com/login/oauth/access_token";
        GithubUser githubUser = null;

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

        GithubToken githubToken = rt.postForObject(address,map,GithubToken.class);

        try {
            githubUser = getAuthorization(githubToken);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if(githubUser == null){
            return;
        }
        System.out.println(githubUser.getLogin()+" "+githubUser.getName()+" "+githubUser.getId());

        if(!githubUserDao.isExistId(githubUser)){
            System.out.println("ssssss");
        }else{
            System.out.println("fffff");
        }
    }


    private GithubUser getAuthorization(GithubToken githubToken) throws JsonProcessingException {
        String address = "https://api.github.com/user";

        HttpHeaders header = new HttpHeaders();
        header.setBearerAuth(githubToken.getAccessToken());

        RestTemplate rt = new RestTemplate();
        HttpEntity httpEntity = new HttpEntity(header);

        ResponseEntity<String> response = rt.exchange(address,HttpMethod.GET,httpEntity,String.class);

        if(response.getStatusCode() != HttpStatus.OK){
            return null;
        }

        JsonObject jsonObject = (JsonObject) JsonParser.parseString(response.getBody());
        GithubUser githubUser = new GithubUser(jsonObject.get("login").getAsString(),jsonObject.get("id").getAsLong(),jsonObject.get("avatar_url").getAsString(),jsonObject.get("name").getAsString());

        return githubUser;
    }
}
