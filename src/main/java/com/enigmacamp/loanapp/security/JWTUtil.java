package com.enigmacamp.loanapp.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.enigmacamp.loanapp.model.entity.AppUsers;
import com.enigmacamp.loanapp.model.entity.Role;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class JWTUtil {
    @Value("${app.loanapp.jwt.secret-key}")
    private String jwtSecret;

    @Value("${app.loanapp.jwt.app-name}")
    private String appName;

    @Value("${app.loanapp.jwt.expire-time}")
    private long jwtExpired;

    private Algorithm algorithm;

    @PostConstruct
    public void init() {
        this.algorithm = Algorithm.HMAC256(jwtSecret.getBytes());
    }

    public String generateToken(AppUsers appUsers) {
        Set<Role> roles = appUsers.getRoles();
        String role = String.valueOf(roles.iterator().next().getRole());
        String sign = JWT.create()
                .withIssuer(appName)
                .withSubject(appUsers.getEmail())
                .withExpiresAt(Date.from(Instant.now().plusSeconds(jwtExpired)))
                .withIssuedAt(Date.from(Instant.now()))
                .withClaim("role",role)
                .sign(algorithm);
        System.out.println(role);
        return sign;
    }

    public boolean validateToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getIssuer().equals(appName);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Map<String, String> getUserInfoByToken(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        Map<String, String> map = new HashMap<>();
        map.put("userId", decodedJWT.getSubject());
        map.put("role", decodedJWT.getClaim("role").asString());
        return map;
    }


}
