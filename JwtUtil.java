package com.example.FactoryShiftAttendanceSystem.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private static final String SECERT_KEY="";
    private static final long EXPIRATION=1000*60*60;
    private static Key getKey(){
        return Keys.hmacShaKeyFor(SECERT_KEY.getBytes());
    }
    public static String generateToken(String email){
        return Jwts.builder().setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis()+EXPIRATION)).signWith(getKey(), SignatureAlgorithm.ES256).compact();
    }
    public static String getEmail(String token){
        return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody().getSubject();
    }

}
