package com.maplog.b.login.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Data
@ConfigurationProperties(prefix="app.auth")
public class JwtService {
    private String secret;
    private long tokenExpirationMsec;

    public String createJwtToken(String login,String name,long id){
        String token=null;

        Algorithm algorithm = Algorithm.HMAC256(secret);

        Date issuedDate = new Date();
        Date notBeforeDate = new Date(issuedDate.getTime());
        Date expiredDate = new Date(issuedDate.getTime()+(tokenExpirationMsec*1000L));

        token= JWT.create()
                .withIssuer("auth0")
                .withSubject(name)
                .withAudience("ugongjae")
                .withClaim("login",login)
                .withClaim("id",id)
                .withNotBefore(notBeforeDate)
                .withExpiresAt(expiredDate)
                .sign(algorithm);

        return token;
    }
}
