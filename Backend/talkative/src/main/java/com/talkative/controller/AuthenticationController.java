package com.talkative.controller;

import com.talkative.dto.SignupResponse;
import com.talkative.dto.SignupRequest;
import com.talkative.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/auth")
public class AuthenticationController {

    @Autowired
    public AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> register(@RequestBody @Valid SignupRequest signupRequest) {

        authenticationService.signup(signupRequest);
        return ResponseEntity.accepted().build();
    }
}
