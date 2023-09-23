package com.eNote.eNote_core.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @author Alterranius
 */
@Component
public class JWTUtil {
    @Value("${jwt_secret}")
    private String secret;

    private final static Logger JWTLogger = LoggerFactory.getLogger(JWTUtil.class);

    public String generateToken(String username) {
        Date expirationDate = Date.from(ZonedDateTime.now().plusMinutes(120).toInstant());
        return JWT.create()
                .withSubject("Account Data details")
                .withClaim("username", username)
                .withIssuedAt(new Date())
                .withIssuer("eNote_core")
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC256(secret));

    }

    public String validateTokenAndRetrieveClaim(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("Account Data details")
                .withIssuer("eNote_core")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        JWTLogger.trace("JWT {} was decoded", jwt.getToken());
        return jwt.getClaim("username").asString();
    }
}
