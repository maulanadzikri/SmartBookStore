package com.smartbookstore.client.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.smartbookstore.client.models.Book;
import com.smartbookstore.client.models.User;
import com.smartbookstore.client.models.UserDetail;
import com.smartbookstore.client.models.dto.request.BookRequest;
import com.smartbookstore.client.models.dto.request.LoginRequest;
import com.smartbookstore.client.models.dto.request.RegistrationRequest;
import com.smartbookstore.client.models.dto.response.LoginResponse;
import com.smartbookstore.client.models.dto.response.RegistrationResponse;
import com.smartbookstore.client.utils.AuthSessionsUtil;

import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.base.url}/auth/login")
    private String url;

    @Value("http://localhost:9090/auth/registration")
    private String registerUrl;

    public Boolean login(LoginRequest loginRequest) {
        try {
            ResponseEntity<LoginResponse> res = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    new HttpEntity<>(loginRequest),
                    LoginResponse.class);

            if (res.getStatusCode() == HttpStatus.OK) {
                setPrinciple(res.getBody(), loginRequest.getPassword());

                Authentication auth = AuthSessionsUtil.getAuthentication();

                // log.info("ID : {}", res.getBody().getId());
                // log.info("Name : {}", auth.getName());
                // log.info("Fullname : {}", res.getBody().getFullname());
                // log.info("Email : {}", res.getBody().getEmail());
                // log.info("Phone : {}", res.getBody().getPhone());
                // log.info("Credentials : {}", auth.getCredentials());
                // log.info("Principal : {}", auth.getPrincipal());
                // log.info("Authorities : {}",res.getBody().getAuthorities());
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public LoginResponse getLoginUser(LoginRequest loginRequest) {
        try {
            ResponseEntity<LoginResponse> res = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    new HttpEntity<>(loginRequest),
                    LoginResponse.class);

            if (res.getStatusCode() == HttpStatus.OK) {
                return res.getBody();
            }
        } catch (Exception e) {
            log.error("Error during login: {}", e.getMessage());
        }
        return null;
    }

    public void setPrinciple(LoginResponse response, String password) {
        List<SimpleGrantedAuthority> authorities = response
                .getAuthorities()
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority))
                .collect(Collectors.toList());

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                response.getUsername(),
                password,
                authorities);

        SecurityContextHolder.getContext().setAuthentication(token);
    }

    public Boolean register(RegistrationRequest registrationRequest) {
        log.info("Registration Request :", registrationRequest);
        try {
            ResponseEntity<RegistrationResponse> res = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    new HttpEntity<>(registrationRequest),
                    RegistrationResponse.class);

            System.out.println(res);
            return res.getStatusCode() == HttpStatus.OK;
        } catch (Exception e) {
            log.error("Error during registration: {}", e.getMessage());
            return false;
        }
    }
}