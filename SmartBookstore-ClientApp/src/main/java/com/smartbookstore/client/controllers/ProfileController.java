package com.smartbookstore.client.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smartbookstore.client.services.*;
import com.smartbookstore.client.utils.AuthSessionsUtil;
import com.smartbookstore.client.models.*;
import com.smartbookstore.client.models.dto.request.LoginRequest;
import com.smartbookstore.client.models.dto.response.LoginResponse;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/profile")
public class ProfileController {
    private AuthService authService;

    @GetMapping
    public String profileView(Model model) {
        return profileData(model, "profile/profile");
    }

    @GetMapping("/edit")
    public String editView(Model model) {
        return profileData(model, "profile/edit");
    }

    private String profileData(Model model, String viewName) {
        Authentication auth = AuthSessionsUtil.getAuthentication();

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(auth.getName());
        loginRequest.setPassword(auth.getCredentials().toString());

        LoginResponse loginResponse = authService.getLoginUser(loginRequest);
        if (loginResponse != null) {
            model.addAttribute("userId", loginResponse.getId());
            model.addAttribute("btnId", loginResponse.getId());
            model.addAttribute("username", loginResponse.getUsername());
            model.addAttribute("userFullname", loginResponse.getFullname());
            model.addAttribute("userEmail", loginResponse.getEmail());
            model.addAttribute("userPhone", loginResponse.getPhone());
            return viewName;
        } else {
            return "redirect:/login?error=true";
        }
    }
}
