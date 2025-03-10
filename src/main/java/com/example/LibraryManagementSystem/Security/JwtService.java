package com.example.LibraryManagementSystem.Security;

import com.example.LibraryManagementSystem.Model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    //@Value("${jwt.secret.key}")
    //for testing
    private static final String SECRET="TmV3U2VjcmV0S2V5Rm9ySldUU2lnbmluZ1B1cnBvc2VzMTIzNDU2Nzg";
    private final SecretKey secretKey= Keys.hmacShaKeyFor(SECRET.getBytes());


    public String generateToken(String userName)
    {
        Map<String,Object> claims=new HashMap<String,Object>();
        return Jwts.builder()
                .subject(userName)
                .claims(claims)
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusSeconds(86400)))
                .signWith(secretKey)
                .compact();
    }

    public Claims extractAllClaims(String token)
    {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


    public String extractUserName(String token) {
        return extractAllClaims(token).getSubject();
    }

    public boolean isValidToken(String token, UserDetails userDetails) {
        String userName= extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token)
    {
        return extractExpiration(token).before(Date.from(Instant.now()));
    }

    public Date extractExpiration(String token)
    {
        return extractAllClaims(token).getExpiration();
    }

}
