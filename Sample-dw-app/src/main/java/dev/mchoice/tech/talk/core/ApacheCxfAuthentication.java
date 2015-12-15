/*
package dev.mchoice.tech.talk.core;

import org.apache.cxf.rs.security.jose.JoseConstants;
import org.apache.cxf.rs.security.jose.JoseHeaders;
import org.apache.cxf.rs.security.jose.jws.*;
import org.apache.cxf.rs.security.jose.jwt.JwtClaims;
import org.apache.cxf.rs.security.jose.jwt.JwtToken;


public class ApacheCxfAuthentication {
    private String privateKey = "privateKey";
    private int expiryTimeInMinutes;

    public ApacheCxfAuthentication(int expiryTimeInMinutes) {
        this.expiryTimeInMinutes = expiryTimeInMinutes;
    }

    public String createToken(String user) {
        JoseHeaders headers = new JoseHeaders();
        headers.setAlgorithm(JoseConstants.HMAC_SHA_256_ALGO);
        JwtClaims claims = new JwtClaims();
        claims.setIssuer(user);
        long millis = 1;
        claims.setExpiryTime(millis);
        claims.setIssuedAt(System.currentTimeMillis());
        JwtToken token = new JwtToken(headers, claims);
        JwsCompactProducer jws = new JwsJwtCompactProducer(token);
        jws.signWith(new HmacJwsSignatureProvider(privateKey, JoseConstants.HMAC_SHA_256_ALGO));
        return jws.getSignedEncodedJws();
    }

    public boolean validateToken(String encodedToken) {
        try {
            JwsJwtCompactConsumer jws = new JwsJwtCompactConsumer(encodedToken);
            boolean state = jws.verifySignatureWith(new HmacJwsSignatureVerifier(privateKey,
                    JoseConstants.HMAC_SHA_256_ALGO));
            return state;
        } catch (java.lang.SecurityException e) {
            return false;
        }
    }

    public void setExpiryTimeInMinutes(int expiryTimeInMinutes) {
        this.expiryTimeInMinutes = expiryTimeInMinutes;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
}
*/
