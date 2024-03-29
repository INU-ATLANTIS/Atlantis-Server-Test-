package com.atl.map.provider;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {
    
    @Value("${secret-key}")
    private String secretKey; 

    public String create(String userEmail){ 

        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));
        

        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        String jwt = Jwts.builder()
            .signWith(key, SignatureAlgorithm.HS256)
            .setSubject(userEmail)
            .setIssuedAt(new Date())
            .setExpiration(expiredDate)
            .compact();

        return jwt; 
    }

    public String validate(String jwt){
        
        String subject = null;

        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        try {
            subject = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
            
            
        }catch(Exception exception){
            exception.printStackTrace();
            return null;
        }

        return subject;
    }
}
