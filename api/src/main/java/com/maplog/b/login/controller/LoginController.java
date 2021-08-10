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
    public void getAuthByGithub(@RequestParam String code){
        githubLogin.getAccessToken("fb9f94e681ecf0b71bbe");
    }

}
