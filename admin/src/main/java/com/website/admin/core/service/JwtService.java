package com.website.admin.core.service;

import com.website.admin.core.constant.JwtTokenConstants;
import com.website.admin.user.entity.Admin;
import com.nimbusds.jwt.JWTClaimsSet;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${jwt.key}")
    private String key;

    private static String SECRET_KEY;

    @Value("${jwt.key}")
    public void setSecretKeyStatic(String key) {
        JwtService.SECRET_KEY = key;
    }

    public String extractUsername(String token) {
        return extractClaim(token, (Claims::getSubject));
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    public String generateToken(UserDetails userDetails) {
        return generateToken(getClaims(userDetails), userDetails);
    }

    public String generateToken(JWTClaimsSet extraClaims, UserDetails userDetails) {
        Admin admin = (Admin) userDetails;
        return Jwts
                .builder()
                .setClaims(extraClaims.getClaims())
                .setSubject(userDetails.getUsername())
//                .claim("roles", userDetails.getAuthorities())
                .claim("uid", admin.getId())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 *60 * 60 * 24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private JWTClaimsSet getClaims(UserDetails userDetails) {
        JWTClaimsSet.Builder jwtClaimsSet = getCommonClaims(userDetails);
        return jwtClaimsSet.build();
    }
//    private JWTClaimsSet getMinimalClaims(UserDetails userDetails) {
//        JWTClaimsSet.Builder jwtClaimsSet = getCommonClaim(userDetails);
//
//        jwtClaimsSet.claim("uid", ((Admin) userDetails).getId());
//        jwtClaimsSet.claim("grp", ((Admin) userDetails).getAccessGroup().getName());
//
//        return jwtClaimsSet.build();
//    }
//    private JWTClaimsSet.Builder getCommonClaim(UserDetails userDetails) {
//        Admin admin = (Admin) userDetails;
//
//        return new JWTClaimsSet.Builder()
//                .audience(JwtTokenConstants.ADMIN)
//                .subject(admin.getUsername())
//                .issuer(JwtTokenConstants.COSMO);
//    }

    private JWTClaimsSet.Builder getCommonClaims(UserDetails userDetails) {

        Admin admin = (Admin) userDetails;
        JWTClaimsSet.Builder jwtClaimsSet = new JWTClaimsSet.Builder()
                .audience(JwtTokenConstants.ADMIN)
                .subject(JwtTokenConstants.AUTH)
                .issuer(JwtTokenConstants.COSMO)
                .claim(JwtTokenConstants.USER_ID, admin.getId())
                .claim(JwtTokenConstants.USERNAME, admin.getUsername())
                .claim(JwtTokenConstants.GROUP, admin.getAccessGroup().getName());

        return jwtClaimsSet;
    }

    private boolean isTemp(UserDetails user) {
        return false;
    }

}
