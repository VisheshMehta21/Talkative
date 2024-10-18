package com.talkative.service;

import com.talkative.dto.SignupRequest;
import com.talkative.dto.SignupResponse;
import com.talkative.entity.Users;
import com.talkative.repository.UsersRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    public UsersRepository usersRepository;

    @Override
    public SignupResponse signup(SignupRequest signupRequest) {

        Optional<Users> user = usersRepository.findByEmail(signupRequest.getEmail());

        if(user.isPresent()) {

            SignupResponse signupResponse = new SignupResponse();
        }

        Users users = new Users();
        users.setFirstname(signupRequest.getFirstName());
        users.setLastName(signupRequest.getLastName());
        users.setEmail(signupRequest.getEmail());
        users.setPassword(signupRequest.getPassword());

        return null;
    }
}
