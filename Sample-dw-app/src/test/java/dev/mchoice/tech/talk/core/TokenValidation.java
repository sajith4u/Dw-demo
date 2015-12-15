package dev.mchoice.tech.talk.core;

import java.text.SimpleDateFormat;
import java.util.Date;


public class TokenValidation {
    @org.junit.Test
    public void testValidateTest() throws Exception {
        float expiryTime = 0.1f;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        System.out.println("Current date " + date);
        JoseAuthentication joseAuthentication = new JoseAuthentication("Sub", expiryTime, 1);
        String token = joseAuthentication.generateToken("Sajith");
        System.out.println("Token : " + token);
        int afterTime = (int) ((expiryTime * 60 * 1000) + 10000);
        // Thread.sleep(afterTime);
        System.out.println("After Date " + simpleDateFormat.format(new Date()));
        String pastToken = "eyJraWQiOiJrZXkxIiwiYWxnIjoiUlMyNTYifQ.eyJpc3MiOiJTYWppdGgiLCJleHAiOjE0NDk2NTYyMTEsImp0aSI6InZXUFhqYVNyNGtuTkY5b1FLWHJjM0EiLCJpYXQiOjE0NDk2NTYxNTEsIm5iZiI6MTQ0OTY1NjAzMSwic3ViIjoiU3ViIn0.X9Vtr9H3uTFjasqnziN8P9PeDzZaIPiBt2Q80Eqi3JOMKyVXSzjXrhunJx4Bc6wFkL7g_UtWwNB57Orh9aCGyADM2RwBrwec8stvJKZD4stEpqr0JJvn-CZTwmokpNpzl5A3tyHYK0_GFTuY96JWCPW9KRpQ817rIIaYoUzla_zGLO8rG94ikit0UsBn_bqIpLUzCpZL6FKblsdCzDobs_-fp_ChrFL0OiNjNX1Dt9iAkB4heQRe05nNz1qPRcqKG8ooy4oVp5O8KN9lPocIjwSq1RHHX0WQ2xTfy919hjMv9-P-8VxpUMdj6_BSgM1co5Pu1YpGHeAEYNPQwuYzWg";
        boolean state = joseAuthentication.validateToken(pastToken);
        System.out.println("Status : " + state);
    }
}
