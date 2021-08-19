package com.maplog.b.login.controller;

import com.maplog.b.login.service.GithubLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private GithubLogin githubLogin;

    @GetMapping(value="/oauth/github")
    public String getAuthByGithub(@RequestParam String code){
        return githubLogin.getAccessToken(code);
    }


    @GetMapping(value="/test")
    public String test(){
        return "hello";
    }
}
