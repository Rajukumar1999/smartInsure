package com.smartInsure.auth_service.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private static  final Key KEY = Keys.hmacShaKeyFor("smartinsure-secret-key-which-is-very-secure".getBytes());

    public static String generatedToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(KEY)
                .compact();
    }

    public static boolean validateToken(String token) {
        try{
            Jwts.parserBuilder()
                    .setSigningKey(KEY)
                    .build()
                    .parseClaimsJws(token);
            return  true;
        }catch (Exception e){
            return  false;
        }
    }
    public String extractUsername(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

}
