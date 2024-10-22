package com.talkative.service;

import com.talkative.dto.LoginRequest;
import com.talkative.dto.OtpVerificationDto;
import com.talkative.dto.SignupRequest;
import com.talkative.dto.SignupResponse;
import com.talkative.entity.Users;
import com.talkative.repository.UsersRepository;
import com.talkative.utility.MessageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    public UsersRepository usersRepository;

    @Autowired
    public OtpService otpService;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Override
    public SignupResponse signup(SignupRequest signupRequest) {

        SignupResponse signupResponse = new SignupResponse();

        String email = signupRequest.getEmail();

        Optional<Users> users = usersRepository.findByEmail(email);

        if(users.isPresent()) {
            signupResponse.setEmail(email);
            signupResponse.setMessage(MessageConstants.USER_EXISTS);
        }

        else {

            Users user = new Users();
            user.setFirstname(signupRequest.getFirstName());
            user.setLastName(signupRequest.getLastName());
            user.setEmail(signupRequest.getEmail());
            user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
            user.setIsVerified(true);

            user = usersRepository.save(user);

            otpService.saveOtp(user);

            signupResponse.setEmail(user.getEmail());
            signupResponse.setMessage(MessageConstants.SIGNUP_SUCCESSFUL);
        }

        return signupResponse;
    }

    @Override
    public void login(LoginRequest loginRequest) {


    }


    @Override
    public boolean verifyOtp(OtpVerificationDto otpVerificationDto) {

        return false;
    }


}
