//package com.business.coffeshop.config;
//
//import io.jsonwebtoken.*;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//@Component
//public class TokenProvider {
//
//    private final String SIGNING_KEY = "your-secret-key";
//    private final long EXPIRATION_TIME = 86400000; // 1 ngày
//
//    public String generateToken(Authentication authentication) {
//        String authorities = authentication.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining(","));
//
//        return Jwts.builder()
//                .setSubject(authentication.getName())
//                .claim("roles", authorities)
//                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                .compact();
//    }
//
//    public String getUsernameFromToken(String token) {
//        return Jwts.parser().setSigningKey(SIGNING_KEY).parseClaimsJws(token).getBody().getSubject();
//    }
//
//    public boolean validateToken(String token, UserDetails userDetails) {
//        String username = getUsernameFromToken(token);
//        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
//    }
//
//    private boolean isTokenExpired(String token) {
//        return Jwts.parser().setSigningKey(SIGNING_KEY).parseClaimsJws(token).getBody().getExpiration().before(new Date());
//    }
//
//    public UsernamePasswordAuthenticationToken getAuthentication(String token, Authentication existingAuth, UserDetails userDetails) {
//        Collection<? extends GrantedAuthority> authorities =
//                Arrays.stream(Jwts.parser().setSigningKey(SIGNING_KEY).parseClaimsJws(token).getBody()
//                                .get("roles", String.class).split(","))
//                        .map(SimpleGrantedAuthority::new)
//                        .collect(Collectors.toList());
//
//        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
//    }
//}
