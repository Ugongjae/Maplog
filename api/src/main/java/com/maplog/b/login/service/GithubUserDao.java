package com.maplog.b.login.service;

import com.maplog.b.login.model.GithubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GithubUserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean isExistId(GithubUser githubUser){
        String query = "SELECT EXISTS(SELECT 1 FROM GithubUser WHERE login=? and id=?)";
        return jdbcTemplate.queryForObject(query,Boolean.class,githubUser.getLogin(),githubUser.getId());
    }

    public int insertUser(GithubUser githubUser){
        String query = "INSERT INTO GithubUser(login,id,avatar_url,name) VALUES(?,?,?,?)";
        return jdbcTemplate.update(query,githubUser.getLogin(),
                githubUser.getId(),
                githubUser.getAvatarUrl(),
                githubUser.getName());
    }
}
