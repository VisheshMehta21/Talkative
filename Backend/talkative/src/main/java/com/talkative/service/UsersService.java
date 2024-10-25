package com.talkative.service;

import com.talkative.dto.UsersDto;

import java.util.List;

public interface UsersService {

    public List<UsersDto> searchUsers(String query);
}
