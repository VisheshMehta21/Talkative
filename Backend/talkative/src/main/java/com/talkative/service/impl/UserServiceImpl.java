package com.talkative.service.impl;

import com.talkative.dto.UpdateUserReq;
import com.talkative.dto.UsersProfileDto;
import com.talkative.dto.UsersProfilePicUpdateDto;
import com.talkative.dto.UsersProfileUpdateDto;
import com.talkative.entity.Users;
import com.talkative.exception.EmailNotFoundException;
import com.talkative.repository.UsersRepository;
import com.talkative.service.FileStorageService;
import com.talkative.service.UsersService;
import com.talkative.utility.MessageConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UsersService {

    @Autowired
    public UsersRepository usersRepository;

    @Autowired
    public FileStorageService fileStorageService;

    @Autowired
    JwtService jwtService;

    @Value("${minio.bucket-name}")
    private String bucketName;

    @Override
    public Users findUserById(Long userId) {

        return usersRepository.findById(userId)
                .orElseThrow(() -> new EmailNotFoundException(MessageConstants.USER_NOT_EXIST));
    }

    @Override
    public List<UsersProfileDto> searchUsers(String query) {
        if (query == null || query.isEmpty()) {
            return Collections.emptyList();
        }

        return usersRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(query, query, query)
                .stream()
                .map(user -> {
                    UsersProfileDto usersProfile = new UsersProfileDto();

                    usersProfile.setUserId(user.getId());
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

        usersProfile.setUserId(user.getId());
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
    public UsersProfileDto updateUserProfilePic(UsersProfilePicUpdateDto usersProfilePicUpdateDto, String email) throws Exception {

        String profileUrl = fileStorageService.uploadFile(usersProfilePicUpdateDto.getProfilePicture());

        log.info("Profile pic url {} for user {}.", profileUrl, email);
        Users user = usersRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException(MessageConstants.USER_NOT_EXIST));

        user.setProfileUrl(profileUrl);

        user = usersRepository.save(user);

        UsersProfileDto usersProfile = new UsersProfileDto();

        usersProfile.setUserId(user.getId());
        usersProfile.setFirstName(user.getFirstName());
        usersProfile.setLastName(user.getLastName());
        usersProfile.setEmail(user.getEmail());
        usersProfile.setCreatedAt(user.getCreatedAt());
        usersProfile.setProfileUrl(user.getProfileUrl());
        usersProfile.setVerified(user.getIsVerified());

        return usersProfile;
    }

    @Override
    public Users findUserFromToken(String jwtToken) {

        String email = jwtService.extractUsername(jwtToken);

        if(email==null) {
            throw new BadCredentialsException("Invalid token ");
        }

        return usersRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException(MessageConstants.USER_NOT_EXIST));

    }


    @Override
    public UsersProfileDto updateUser(Long id, UpdateUserReq req) {
        Users user = findUserById(id);


        if(req.getFirstName()!=null) {
            user.setFirstName(req.getFirstName());
        }
        if(req.getLastName()!=null) {
            user.setLastName(req.getLastName());
        }
        if(req.getProfilePicture()!=null) {
            user.setProfileUrl(req.getProfilePicture());
        }
        user = usersRepository.save(user);

        UsersProfileDto usersProfile = new UsersProfileDto();

        usersProfile.setUserId(user.getId());
        usersProfile.setFirstName(user.getFirstName());
        usersProfile.setLastName(user.getLastName());
        usersProfile.setCreatedAt(user.getCreatedAt());
        usersProfile.setProfileUrl(user.getProfileUrl());
        usersProfile.setEmail(user.getEmail());
        return usersProfile;
    }
}
