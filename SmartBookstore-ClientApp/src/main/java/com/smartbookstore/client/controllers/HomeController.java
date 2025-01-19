package com.smartbookstore.client.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.smartbookstore.client.models.UserDetail;
import com.smartbookstore.client.models.dto.request.LoginRequest;
import com.smartbookstore.client.models.dto.response.LoginResponse;
import com.smartbookstore.client.services.AuthService;
import com.smartbookstore.client.utils.AuthSessionsUtil;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
    private AuthService authService;
    private UserDetail user;

    @GetMapping("/home")
    public String dashboard(Model model) {
        model.addAttribute("isActive", "home");
    
        Authentication auth = AuthSessionsUtil.getAuthentication();
    
        boolean isAdmin = auth.getAuthorities().stream()
                            .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
    
        if (isAdmin) {
            return "redirect:/dashboard";
        } else {
            return "index";
        }
    }

    @GetMapping("/bestseller")
    public String getAllBestSeller(Model model){
        return "book/bestseller";
    }
}
