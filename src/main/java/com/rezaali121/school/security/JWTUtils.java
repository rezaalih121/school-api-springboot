package com.rezaali121.school.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class JWTUtils {

    // this is how we declare a variable in application.properties to be used here
    @Value("${jwt.secret}")
    private String secret;

    // getting the informations of JWT ( like : creation date , expiration date)
    public Claims extractBody(String jwt){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJwt(jwt)
                .getBody();

    }

    public String generateJwt(MyUserDetails myUserDetails){
        return Jwts.builder()
                .setSubject(myUserDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }


}
