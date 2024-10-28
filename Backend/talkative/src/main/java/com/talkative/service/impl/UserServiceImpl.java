package com.talkative.service.impl;

import com.talkative.dto.UsersDto;
import com.talkative.entity.Users;
import com.talkative.exception.EmailNotFoundException;
import com.talkative.repository.UsersRepository;
import com.talkative.service.UsersService;
import com.talkative.utility.MessageConstants;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UsersService {

    @Autowired
    public UsersRepository usersRepository;

    @Override
    public List<UsersDto> searchUsers(String query) {
        if (query == null || query.isEmpty()) {
            return Collections.emptyList();
        }

        return usersRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(query, query, query)
                .stream()
                .map(user -> {
                    UsersDto usersProfile = new UsersDto();
                    usersProfile.setFirstName(user.getFirstName());
                    usersProfile.setLastName(user.getLastName());
                    usersProfile.setCreatedAt(user.getCreatedAt());
                    usersProfile.setProfileUrl(user.getProfileUrl());
                    usersProfile.setEmail(user.getEmail());
                    return usersProfile;
                })
                .collect(Collectors.toList());
    }

    @Override
    public UsersDto findUserByEmail(String email) {

        Users user = usersRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException(MessageConstants.USER_NOT_EXIST));

        UsersDto usersProfile = new UsersDto();

        usersProfile.setFirstName(user.getFirstName());
        usersProfile.setLastName(user.getLastName());
        usersProfile.setEmail(user.getEmail());
        usersProfile.setCreatedAt(user.getCreatedAt());
        usersProfile.setProfileUrl(user.getProfileUrl());
        usersProfile.setVerified(user.getIsVerified());

        return usersProfile;
    }
}
