package com.talkative.controller;

import com.talkative.dto.UsersDto;
import com.talkative.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UsersController {

    @Autowired
    public UsersService usersService;

    @GetMapping("/search")
    public ResponseEntity<List<UsersDto>> searchUsers(@RequestParam(value = "query", required = false) String query) {

        log.info("User searched for users containing : " + query);
        List<UsersDto> users = usersService.searchUsers(query);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
