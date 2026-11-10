package com.invex.employservice.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secretKey);
    }

    public String generateToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtExpiration))
                .sign(getAlgorithm());
    }

    public String extractUsername(String token) {
        return verifyToken(token).getSubject();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        try {
            DecodedJWT decoded = verifyToken(token);
            return decoded.getSubject().equals(userDetails.getUsername())
                    && decoded.getExpiresAt().after(new Date());
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    private DecodedJWT verifyToken(String token) {
        return JWT.require(getAlgorithm())
                .build()
                .verify(token);
    }
}
