package com.talkative.controller;

import com.talkative.dto.LoginRequest;
import com.talkative.dto.LoginResponse;
import com.talkative.dto.SignupResponse;
import com.talkative.dto.SignupRequest;
import com.talkative.entity.Users;
import com.talkative.service.AuthenticationService;
import com.talkative.service.impl.JwtService;
import jakarta.validation.Valid;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth")
public class AuthenticationController {

    @Autowired
    public AuthenticationService authenticationService;

    @Autowired
    public JwtService jwtService;

    @PostMapping("/signup")
    public ResponseEntity<?> registrationHandler(@RequestBody @Valid SignupRequest signupRequest) {

        SignupResponse signupResponse = authenticationService.signup(signupRequest);
        return new ResponseEntity<SignupResponse>(signupResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginHandler(@RequestBody @Valid LoginRequest loginRequest) {

        Users authenticatedUser = authenticationService.login(loginRequest);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setJwtToken(jwtToken);

        return ResponseEntity.ok(loginResponse);
    }
}
