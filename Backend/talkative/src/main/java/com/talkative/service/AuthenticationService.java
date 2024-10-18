package com.talkative.service;

import com.talkative.dto.SignupResponse;
import com.talkative.dto.SignupRequest;

public interface AuthenticationService {

    public SignupResponse signup(SignupRequest signupRequest);
}
