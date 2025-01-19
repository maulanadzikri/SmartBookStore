package com.smartbookstore.client.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
@RequestMapping("/register")
public class RegisterController {

    private AuthService authService;

    @GetMapping
    public String registerView(Model model, RegistrationRequest registerRequest) {
        Authentication auth = AuthSessionsUtil.getAuthentication();
        model.addAttribute("registerRequest", registerRequest);
        if (auth instanceof AnonymousAuthenticationToken) {
            return "auth/register";
        }

        return "redirect:/login";
    }

    @PostMapping
    public String register(Model model, @ModelAttribute("registerRequest") RegistrationRequest registerRequest) {
        log.info("Registration Request :", registerRequest);
        if (authService.register(registerRequest)) {
            return "redirect:/login";
        } else {
            model.addAttribute("error", "Registration failed. Please try again.");
            return "auth/register";
        }
    }
}
