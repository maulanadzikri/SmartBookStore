package com.smartbookstore.client.controllers;

import java.util.List;

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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/history")
public class HistoryController {
    private AuthService authService;
    private OrderService orderService;

    @GetMapping
    public String history(Model model) {
        Authentication auth = AuthSessionsUtil.getAuthentication();
        LoginResponse loginResponse = getLoginResponseFromAuth(auth);

        if (loginResponse != null) {
            log.info("User ID : ", loginResponse.getId());
            Integer userId = loginResponse.getId();
            List<Order> history = orderService.getById(userId);
            model.addAttribute("history", history);
            return profileData(model, "profile/history");
        } else {
            return "redirect:/login?error=true";
        }
    }

    private LoginResponse getLoginResponseFromAuth(Authentication auth) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(auth.getName());
        loginRequest.setPassword(auth.getCredentials().toString());

        return authService.getLoginUser(loginRequest);
    }

    private String profileData(Model model, String viewName) {
        Authentication auth = AuthSessionsUtil.getAuthentication();

        LoginResponse loginResponse = getLoginResponseFromAuth(auth);
        if (loginResponse != null) {
            return viewName;
        } else {
            return "redirect:/login?error=true";
        }
    }
}


