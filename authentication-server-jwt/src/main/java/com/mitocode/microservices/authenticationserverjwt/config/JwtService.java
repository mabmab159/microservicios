package com.mitocode.microservices.authenticationserverjwt.config;

import com.mitocode.microservices.authenticationserverjwt.model.entity.UserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;

@Service
public class JwtService {

    @Value("${mitocode.security.key:mitocode}")
    private String mitocodeKey;

    public String generateToken(UserDetails userDetails) {
        UserEntity userEntity = (UserEntity) userDetails;
        Map<String, Object> customClaims = new HashMap<>();
        customClaims.put("email", userEntity.getEmail());
        customClaims.put("lastname", userEntity.getLastname());
        customClaims.put("password", userEntity.getPassword());
        customClaims.put("authorities", userEntity.getAuthorities());
        return Jwts.builder()
                .setSubject("USERNAME")
                .setIssuedAt(new Date())
                .signWith(getSignKey())
                .setExpiration(new Date(System.currentTimeMillis() + (3600 * 1000L)))
                .addClaims(customClaims)
                .compact();
    }

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(Base64.getEncoder().encode(mitocodeKey.getBytes()));
    }
}
