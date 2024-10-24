package com.talkative.service;

import com.talkative.dto.LoginRequest;
import com.talkative.dto.SignupRequest;
import com.talkative.dto.SignupResponse;
import com.talkative.entity.Users;
import com.talkative.exception.UserAlreadyExistsException;
import com.talkative.exception.EmailNotFoundException;
import com.talkative.repository.UsersRepository;
import com.talkative.utility.MessageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    public UsersRepository usersRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationManager authenticationManager;

    @Override
    public SignupResponse signup(SignupRequest signupRequest) {
        SignupResponse signupResponse = new SignupResponse();
        String email = signupRequest.getEmail();

        // Check if the user already exists, throw exception if true
        usersRepository.findByEmail(email)
                .ifPresent(user -> {
                    throw new UserAlreadyExistsException(MessageConstants.USER_EXISTS);
                });

        // If user does not exist, proceed with signup
        Users user = new Users();
        user.setFirstname(signupRequest.getFirstName());
        user.setLastName(signupRequest.getLastName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setIsVerified(true);

        // Save the new user to the database
        user = usersRepository.save(user);

        // Prepare a successful signup response
        signupResponse.setMessage(MessageConstants.SIGNUP_SUCCESSFUL);
        signupResponse.setEmail(user.getEmail());

        return signupResponse;
    }

    @Override
    public Users login(LoginRequest loginRequest) {

        // Fetch the user by email first
        Users user = usersRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new EmailNotFoundException(MessageConstants.USER_NOT_EXIST));

        // Check if the user's email is verified
        if (!user.getIsVerified()) {
            throw new IllegalStateException(MessageConstants.VERIFY_EMAIL);
        }

        // Authenticate the user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        return user; // Return the authenticated use

    }

}
