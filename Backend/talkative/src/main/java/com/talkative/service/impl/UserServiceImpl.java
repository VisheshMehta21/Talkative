package com.talkative.service.impl;

import com.talkative.dto.UsersDto;
import com.talkative.repository.UsersRepository;
import com.talkative.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
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
                    UsersDto usersDto = new UsersDto();
                    usersDto.setFirstName(user.getFirstName());
                    usersDto.setLastName(user.getLastName());
                    usersDto.setCreatedAt(user.getCreatedAt());
                    usersDto.setProfileUrl(user.getProfileUrl());
                    usersDto.setEmail(user.getEmail());
                    return usersDto;
                })
                .collect(Collectors.toList());
    }
}
