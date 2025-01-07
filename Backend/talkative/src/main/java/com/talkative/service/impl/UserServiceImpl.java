package com.talkative.service.impl;

import com.talkative.dto.UsersProfileDto;
import com.talkative.dto.UsersProfilePicUpdateDto;
import com.talkative.dto.UsersProfileUpdateDto;
import com.talkative.entity.Users;
import com.talkative.exception.EmailNotFoundException;
import com.talkative.repository.UsersRepository;
import com.talkative.service.FileStorageService;
import com.talkative.service.UsersService;
import com.talkative.utility.MessageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UsersService {

    @Autowired
    public UsersRepository usersRepository;

    @Autowired
    public FileStorageService fileStorageService;

    @Override
    public List<UsersProfileDto> searchUsers(String query) {
        if (query == null || query.isEmpty()) {
            return Collections.emptyList();
        }

        return usersRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(query, query, query)
                .stream()
                .map(user -> {
                    UsersProfileDto usersProfile = new UsersProfileDto();
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
    public UsersProfileDto getUserProfile(String email) {

        Users user = usersRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException(MessageConstants.USER_NOT_EXIST));

        UsersProfileDto usersProfile = new UsersProfileDto();

        usersProfile.setFirstName(user.getFirstName());
        usersProfile.setLastName(user.getLastName());
        usersProfile.setEmail(user.getEmail());
        usersProfile.setCreatedAt(user.getCreatedAt());
        usersProfile.setProfileUrl(user.getProfileUrl());
        usersProfile.setVerified(user.getIsVerified());

        return usersProfile;
    }

    @Override
    public Users findUserByEmail(String email) {
        return usersRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException(MessageConstants.USER_NOT_EXIST));
    }

    @Override
    public UsersProfileDto updateUserProfile(UsersProfileUpdateDto usersProfileUpdateDto) {
        return null;
    }

    @Override
    public void updateUserProfilePic(UsersProfilePicUpdateDto usersProfilePicUpdateDto, String email) throws Exception {

        String profileUrl = fileStorageService.uploadFile(usersProfilePicUpdateDto.getProfilePic());

        Users user = usersRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException(MessageConstants.USER_NOT_EXIST));

        user.setProfileUrl(profileUrl);

        Users updatedUser = usersRepository.save(user);

        String updatedProfileUrl = updatedUser.getProfileUrl();
    }


}
