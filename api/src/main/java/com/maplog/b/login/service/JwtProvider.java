package com.maplog.b.login.service;

import io.jsonwebtoken.*;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Data
@ConfigurationProperties(prefix="app.auth")
public class JwtProvider {
    private String secret;
    private long tokenExpirationMsec;

    public String createJwtToken(String login,String name,long id){
        Date issuedDate = new Date();
        Date notBeforeDate = new Date(issuedDate.getTime());
        Date expiredDate = new Date(issuedDate.getTime()+(tokenExpirationMsec*1000L));

        Map<String,Object> claims = new HashMap<>();
        claims.put("name",name);
        claims.put("id",id);

        return Jwts.builder()
                .setSubject(login)
                .setIssuer("auth0")
                .setAudience("ugongjae")
                .setClaims(claims)
                .setNotBefore(notBeforeDate)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256,secret.getBytes())
                .compact();
    }

    public boolean validateToken(String token){
        if(token == null){
            return false;
        }

        try{
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token);
            return true;
        } catch (SignatureException e){
            System.out.println("ff1");
        } catch (MalformedJwtException e){
            System.out.println("ff2");
        } catch (UnsupportedJwtException e){
            System.out.println("ff3");
        } catch (IllegalArgumentException e){
            System.out.println("ff4");
        }
        return false;
    }

    public String getLoginFromToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(secret.getBytes())
                .parseClaimsJws(token)
                .getBody();
        System.out.println(claims.get("login"));

        return claims.get("login",String.class);
    }

    public String parseTokenString(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if(token != null){
            return token;
        }
        return null;
    }

    private Claims getClaims(String token){
        return Jwts.parser()
                .setSigningKey(secret.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }
}
