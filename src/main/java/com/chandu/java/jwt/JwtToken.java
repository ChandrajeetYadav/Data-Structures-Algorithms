package com.chandu.java.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtToken {
    public static void main(String[] args) {
        String id = "id@1";
        String issuer = "Chandu";
        String subject = "Test subject";
        String secretKey = "qwertyuiop";
        long ttlMillis = 1000 * 10;

        String jwtToken1 = generateJwt(id, issuer, subject, ttlMillis, secretKey);
        System.out.println(jwtToken1);
        System.out.println("Verifying jwtToken 1: ");
        verifyJwtToken(jwtToken1, secretKey);

        System.out.println();

        String jwtToken2 = generateJwt(id, issuer, subject, ttlMillis, secretKey);
        System.out.println(jwtToken2);
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Verifying jwtToken 2: ");
        verifyJwtToken(jwtToken2, secretKey);
    }

    private static String generateJwt(String id, String issuer, String subject, long ttlMillis, String secretKey) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Map<String, Object> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        long nowMillis = System.currentTimeMillis();
        Date date = new Date(nowMillis);
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(secretKey);
        Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
        JwtBuilder jwtBuilder = Jwts.builder().setId(id)
                .setHeaderParam("header1", "headerValue1")
                .setIssuedAt(date)
                .setSubject(subject)
                .setIssuer(issuer)
                .claim("key1", "vaue1")
                .claim("key2", "vaue2")
                .signWith(signatureAlgorithm, signingKey);
        if (ttlMillis > 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            jwtBuilder.setExpiration(exp);
        }
        return jwtBuilder.compact();
    }

    private static void verifyJwtToken(String jwt, String secretKey) {
        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                .parseClaimsJws(jwt)
                .getBody();
        System.out.println(claims);
    }
}
