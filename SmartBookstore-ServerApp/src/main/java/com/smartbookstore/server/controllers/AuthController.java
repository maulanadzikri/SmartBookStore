package com.smartbookstore.server.controllers;

import com.smartbookstore.server.model.dto.request.LoginRequest;
import com.smartbookstore.server.model.dto.request.RegistrationRequest;
import com.smartbookstore.server.model.dto.response.LoginResponse;
import com.smartbookstore.server.model.entity.UserDetail;

public interface AuthController {
    UserDetail registration(RegistrationRequest registrationRequest);
    LoginResponse login(LoginRequest loginRequest);
}