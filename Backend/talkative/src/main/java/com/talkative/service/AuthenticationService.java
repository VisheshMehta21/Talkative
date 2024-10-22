package com.talkative.service;

import com.talkative.dto.LoginRequest;
import com.talkative.dto.SignupResponse;
import com.talkative.dto.SignupRequest;
import com.talkative.entity.Users;

public interface AuthenticationService {

    public SignupResponse signup(SignupRequest signupRequest);
    public Users login(LoginRequest loginRequest);
}
