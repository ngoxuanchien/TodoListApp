package com.todolist_app.todolistapp.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    // Code form: https://github.com/ali-bouali/spring-boot-3-jwt-security.git
    private static final String SECRET_KEY = "635266556A586E3272357538782F413F442A472D4B6150645367566B59703373";

    /**
     * Extract user email form token
     * @param token
     * @return
     */
    public String extractUsername(String token) {
        return  ExtractClaim(token, Claims::getSubject);
    }


    /**
     * Extract informaiton from jwt token
     * @param token
     * @param claimsResolver
     * @return
     * @param <T>
     */
    public <T> T ExtractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Generate Token form User Details
     * @param userDetails
     * @return
     */
    public String GenerateToken(UserDetails userDetails) {
        return GenerateToken(new HashMap<>(), userDetails);
    }

    /**
     * Generate token
     * @param extraClaims
     * @param userDetails
     * @return
     */
    public String GenerateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Check token valid
     * @param token
     * @param userDetails
     * @return
     */
    public boolean IsTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !IsTokenExpired(token);
    }

    /**
     * Check token expired
     * @param token
     * @return
     */
    private boolean IsTokenExpired(String token) {
        return ExtractExpiration(token).before(new Date());
    }

    /**
     * Check token expiration
     * @param token
     * @return
     */
    private Date ExtractExpiration(String token) {
        return ExtractClaim(token, Claims::getExpiration);
    }

    /**
     * Extract all information from token
     * @param token
     * @return
     */
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Get
     * @return
     */
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
