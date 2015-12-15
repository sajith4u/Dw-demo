package dev.mchoice.tech.talk.core;

import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;


public class JoseAuthentication {
    private static final Logger logger = LoggerFactory.getLogger(JoseAuthentication.class);
    private String subject;
    private float expiryTimeInMinutes;
    private int beforeTimeTest;
    RsaJsonWebKey rsaJsonWebKey;

    public JoseAuthentication(String subject, float expiryTimeInMinutes, int beforeTimeTest) {
        this.subject = subject;
        this.expiryTimeInMinutes = expiryTimeInMinutes;
        this.beforeTimeTest = beforeTimeTest;
        try {
            rsaJsonWebKey = RsaJwkGenerator.generateJwk(2048);
            rsaJsonWebKey.setKeyId("key1");
        } catch (JoseException e) {
            logger.error("Error Occurred while Generating Key {}", e);
        }
    }

    public String generateToken(String userName) {
        String jwt = null;
        try {
            JwtClaims claims = new JwtClaims();
            claims.setIssuer(userName);
            claims.setExpirationTimeMinutesInTheFuture(expiryTimeInMinutes);
            claims.setGeneratedJwtId();
            claims.setIssuedAtToNow();
            claims.setNotBeforeMinutesInThePast(beforeTimeTest);
            claims.setSubject(subject);


            JsonWebSignature jws = new JsonWebSignature();
            jws.setPayload(claims.toJson());
            jws.setKey(rsaJsonWebKey.getPrivateKey());
            jws.setKeyIdHeaderValue(rsaJsonWebKey.getKeyId());
            jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);
            jwt = jws.getCompactSerialization();
            logger.info("Token Created {}", jwt);

        } catch (JoseException e) {
            logger.error("Creating Token Failed {}", e);
        } catch (Exception ex) {
            logger.error("Error occurred while Creating Token {}", ex);
        }
        return jwt;
    }


    public boolean validateToken(String token) {
        boolean state;
        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setRequireExpirationTime()
                .setAllowedClockSkewInSeconds(30)
                .setRequireSubject()
                .setRequireExpirationTime()
                .setVerificationKey(rsaJsonWebKey.getKey())
                .build();

        try {
            JwtClaims jwtClaims = jwtConsumer.processToClaims(token);
            long expiredDateTime = jwtClaims.getExpirationTime().getValueInMillis();
            Date date = new Date(expiredDateTime);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            logger.info("Token Expired Date : " + simpleDateFormat.format(date));
            state = true;
        } catch (InvalidJwtException e) {
            state = false;
            logger.debug("Error Occurred while validating JWT {}", e);
        } catch (MalformedClaimException e) {
            state = false;
        }
        return state;
    }
}
