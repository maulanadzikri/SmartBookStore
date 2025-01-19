package com.smartbookstore.client.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smartbookstore.client.models.UserDetail;
import com.smartbookstore.client.models.dto.request.LoginRequest;
import com.smartbookstore.client.models.dto.request.RegistrationRequest;
import com.smartbookstore.client.models.dto.response.LoginResponse;
import com.smartbookstore.client.services.AuthService;
import com.smartbookstore.client.utils.AuthSessionsUtil;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/login")
public class AuthController {

    private AuthService authService;

    @GetMapping
    public String loginView(Model model, LoginRequest loginRequest) {
        Authentication auth = AuthSessionsUtil.getAuthentication();

        if (auth instanceof AnonymousAuthenticationToken) {
            return "auth/login";
        }

        return "redirect:/home";
    }

    @PostMapping
    public String login(LoginRequest loginRequest) {
        if (!authService.login(loginRequest)) {
            return "redirect:/login?error=true";
        }
        return "redirect:/home";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        RegistrationRequest registrationRequest = new RegistrationRequest();
        model.addAttribute("registerRequest", registrationRequest);
        return "S/auth/register";

    }

    @PostMapping("/register")
    public String register(RegistrationRequest registerRequest) {
        log.info("Registered");
        authService.register(registerRequest);
        return "redirect:/auth/login";
    }
}