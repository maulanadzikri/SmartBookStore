package com.smartbookstore.client.utils;

import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.security.core.Authentication;
import java.io.IOException;

public class RequestInterceptorUtil implements ClientHttpRequestInterceptor {

    @Override
    @SuppressWarnings("null")
    public ClientHttpResponse intercept(
        HttpRequest request,
        byte[] body,
        ClientHttpRequestExecution execution
    ) throws IOException {
        Authentication auth = AuthSessionsUtil.getAuthentication();

        if (!request.getURI().getPath().equals("/auth/login")) {
            request.getHeaders().add(
                "Authorization",
                "Basic " +
                BasicHeaderUtil.createBasicToken(
                auth.getName(),
                auth.getCredentials().toString()
                )
            );
        }

        ClientHttpResponse response = execution.execute(request, body);
        return response;
    }
}
