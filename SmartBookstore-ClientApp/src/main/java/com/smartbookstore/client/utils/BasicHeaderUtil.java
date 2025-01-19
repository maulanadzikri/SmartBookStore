package com.smartbookstore.client.utils;

import java.nio.charset.Charset;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;

public class BasicHeaderUtil {
    public static String createBasicToken(String username, String password){
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(
            auth.getBytes(Charset.forName("US-ASCII"))
        );

        return new String(encodedAuth);
    }

    public static HttpHeaders createBasicHeader(){
        Authentication auth = AuthSessionsUtil.getAuthentication();
        String base64token = createBasicToken(auth.getName().toString(), auth.getCredentials().toString());
        return new HttpHeaders(){
            {
                set("Authorization", "Basic " + base64token);
            }
        };
    }
}
