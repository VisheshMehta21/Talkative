package com.talkative.service;

import com.talkative.dto.UsersProfileDto;
import com.talkative.dto.UsersProfilePicUpdateDto;
import com.talkative.dto.UsersProfileUpdateDto;
import com.talkative.entity.Users;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UsersService {

    public List<UsersProfileDto> searchUsers(String query);
    public UsersProfileDto getUserProfile(String email);
    public Users findUserByEmail(String email);
    public UsersProfileDto updateUserProfile(UsersProfileUpdateDto usersProfileUpdateDto);
    public void updateUserProfilePic(UsersProfilePicUpdateDto usersProfilePicUpdateDto, String email) throws Exception;
}
