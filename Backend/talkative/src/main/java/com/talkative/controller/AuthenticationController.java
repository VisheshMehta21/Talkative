package com.talkative.controller;

import com.talkative.dto.LoginRequest;
import com.talkative.dto.OtpVerificationDto;
import com.talkative.dto.SignupResponse;
import com.talkative.dto.SignupRequest;
import com.talkative.entity.Users;
import com.talkative.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@RequestMapping(path = "/auth")
public class AuthenticationController {

    @Autowired
    public AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody @Valid SignupRequest signupRequest) {

        SignupResponse signupResponse = authenticationService.signup(signupRequest);
        return new ResponseEntity<SignupResponse>(signupResponse, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest) {


    }

    @PostMapping("/verifyOtp")
    public ResponseEntity<?> verifyOtp(@RequestBody OtpVerificationDto otpVerificationDto) {

        if(authenticationService.verifyOtp(otpVerificationDto))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
