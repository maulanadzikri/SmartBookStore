package com.smartbookstore.server.controllers.impl;

import com.smartbookstore.server.controllers.AuthController;
import com.smartbookstore.server.model.entity.UserDetail;
import com.smartbookstore.server.model.dto.request.LoginRequest;
import com.smartbookstore.server.model.dto.request.RegistrationRequest;
import com.smartbookstore.server.model.dto.response.LoginResponse;
import com.smartbookstore.server.model.entity.Role;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.smartbookstore.server.services.impl.AuthServiceImpl;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthControllerImpl implements AuthController {
    private AuthServiceImpl authServiceImpl;

    @Override
    @PostMapping("/registration")
    public UserDetail registration(@RequestBody RegistrationRequest registrationRequest) {
        return authServiceImpl.registration(registrationRequest);
    }

    @PutMapping("/add-role/{idUserDetail}")
    public UserDetail addRole(@PathVariable Integer idUserDetail, @RequestBody Role role) {
        return authServiceImpl.addRole(idUserDetail, role);
    }

    @Override
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authServiceImpl.login(loginRequest);
    }
}
