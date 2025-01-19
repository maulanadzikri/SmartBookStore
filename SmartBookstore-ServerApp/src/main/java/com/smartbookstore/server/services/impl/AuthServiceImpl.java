package com.smartbookstore.server.services.impl;

import com.smartbookstore.server.model.entity.UserDetail;
import com.smartbookstore.server.model.dto.request.LoginRequest;
import com.smartbookstore.server.model.dto.request.RegistrationRequest;
import com.smartbookstore.server.model.dto.response.LoginResponse;
import com.smartbookstore.server.model.entity.Role;
import com.smartbookstore.server.model.entity.User;
import com.smartbookstore.server.repositories.UserDetailRepository;
import com.smartbookstore.server.repositories.UserRepository;
import com.smartbookstore.server.services.AuthService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private UserDetailRepository userDetailRepository;
    private UserDetailService userDetailServiceImpl;
    private RoleService roleServiceImpl;
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private AppUserService appUserDetailService;

    @Override
    @Transactional
    public UserDetail registration(RegistrationRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user = userRepository.save(user);
        List<Role> roles = new ArrayList<>();
        roles.add(roleServiceImpl.getById(2));
        user.setRoles(roles);
        UserDetail userDetail = new UserDetail();
        userDetail.setFullname(request.getFullname());
        userDetail.setEmail(request.getEmail());
        userDetail.setPhone(request.getPhone());
        userDetail.setUser(user);
        userDetail = userDetailRepository.save(userDetail);
        user.setUserDetail(userDetail);
        userRepository.save(user);

        return userDetail;
    }

    public UserDetail addRole(Integer idUserDetail, Role role) {
        UserDetail userDetail = userDetailServiceImpl.getById(idUserDetail);
        User user = userDetail.getUser();

        List<Role> roles = user.getRoles();
        roles.add(roleServiceImpl.getById(role.getId()));

        user.setRoles(roles);
        userRepository.save(user);
        return userDetail;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword());

        Authentication auth = authenticationManager.authenticate(authReq);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);

        UserDetails userDetails = appUserDetailService.loadUserByUsername(loginRequest.getUsername());
        User user = userRepository.findByUsername(loginRequest.getUsername()).get();

        List<String> authorities = userDetails.getAuthorities().stream().map(authority -> authority.getAuthority()).collect(Collectors.toList());

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setId(user.getUserDetail().getId());
        loginResponse.setUsername(userDetails.getUsername());
        loginResponse.setFullname(user.getUserDetail().getFullname());
        loginResponse.setEmail(user.getUserDetail().getEmail());
        loginResponse.setPhone(user.getUserDetail().getPhone());
        loginResponse.setAuthorities(authorities);

        return loginResponse;
    }
}
