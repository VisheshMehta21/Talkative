package com.talkative.service;

import com.talkative.dto.UpdateUserReq;
import com.talkative.dto.UsersProfileDto;
import com.talkative.dto.UsersProfilePicUpdateDto;
import com.talkative.dto.UsersProfileUpdateDto;
import com.talkative.entity.Users;

import java.util.List;

public interface UsersService {

    public Users findUserById(Long userId);
    public List<UsersProfileDto> searchUsers(String query);
    public UsersProfileDto getUserProfile(String email);
    public Users findUserByEmail(String email);
    public UsersProfileDto updateUserProfile(UsersProfileUpdateDto usersProfileUpdateDto);
    public UsersProfileDto updateUserProfilePic(UsersProfilePicUpdateDto usersProfilePicUpdateDto, String email) throws Exception;
    public Users findUserFromToken(String jwtToken);
    public UsersProfileDto updateUser(Long id, UpdateUserReq req);
}
