package com.talkative.service;

import com.talkative.dto.LoginRequest;
import com.talkative.dto.OtpVerificationDto;
import com.talkative.dto.SignupResponse;
import com.talkative.dto.SignupRequest;

public interface AuthenticationService {

    public SignupResponse signup(SignupRequest signupRequest);
    public void login(LoginRequest loginRequest);
    public boolean verifyOtp(OtpVerificationDto otpVerificationDto);
}
