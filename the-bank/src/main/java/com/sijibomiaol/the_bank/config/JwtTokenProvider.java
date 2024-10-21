package com.sijibomiaol.the_bank.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenProvider {



    @Value("${app.jwt-expiration}")
    private Long jwtExpirationDate;

    private String privateKey="";

    public JwtTokenProvider() throws NoSuchAlgorithmException{
        KeyGenerator generator = KeyGenerator.getInstance("HmacSHA256");
        SecretKey sk = generator.generateKey();
        privateKey = Base64.getEncoder().encodeToString(sk.getEncoded());
    }

    public String generateToken(Authentication auth) {
        String username = auth.getName();
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + jwtExpirationDate);
        Map<String, Object> claims = new HashMap<String, Object>();


        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(currentDate)
                .expiration(expirationDate)
                .and()
                .signWith(getKey())
                .compact();


    }

    private SecretKey getKey() {

        byte[] encodedKey = Base64.getDecoder().decode(privateKey);
        return Keys.hmacShaKeyFor(encodedKey);
    }

    public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


//    public  boolean validateToken(String token, Authentication auth) {
//        try {
//            Claims claims = extractAllClaims(token);
//            String tokenUsername = extractUsername(token);
//            Date expirationDate = Date.getExpiration();
//
//            if (expirationDate.before(new Date())) {
//                return false;
//            }
//            String username = auth.getName();
//            return username.equals(tokenUsername);
//        }
//        catch (Exception e) {
//            System.out.println("Invalid or Expired JWT token" + e.getMessage());
//
//            return false;
//        }
//    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = this.extractUsername(token);
        return(username.equals(userDetails.getUsername()) && !isExpiredToken(token));
    }

    private boolean isExpiredToken(String token) {
        return extractExpiration(token).before(new Date());

    }

    private Date extractExpiration(String token) {

        return extractClaim(token, Claims::getExpiration);
    }


}
