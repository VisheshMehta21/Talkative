package com.talkative.controller;

import com.talkative.apiresponse.ApiResponse;
import com.talkative.dto.UpdateUserReq;
import com.talkative.dto.UsersProfileDto;
import com.talkative.dto.UsersProfilePicUpdateDto;
import com.talkative.dto.UsersProfileUpdateDto;
import com.talkative.entity.Users;
import com.talkative.service.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j

public class UsersController {

    @Autowired
    public UsersService usersService;

    @GetMapping("/search")
    public ResponseEntity<List<UsersProfileDto>> searchUsers(@RequestParam(value = "query", required = false) String query) {

        log.info("User searched for users containing : {}", query);
        List<UsersProfileDto> users = usersService.searchUsers(query);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<UsersProfileDto> getUserProfile(HttpServletRequest request) {

        // Get the authentication from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Extract email from the authentication (after JwtAuthenticationFilter sets it)
        String email = authentication.getName();

        UsersProfileDto usersProfile = usersService.getUserProfile(email);
        log.info("User profile :: loged in {}", usersProfile);
        return new ResponseEntity<UsersProfileDto>(usersProfile, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<UsersProfileDto> updateUserHandler(HttpServletRequest httpServletRequest, @RequestBody UpdateUserReq updateUserReq) {

        // Get the authentication from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Extract email from the authentication (after JwtAuthenticationFilter sets it)
        String email = authentication.getName();

        log.info("Updating profile for user {}.", updateUserReq.getFirstName());

        Users user = usersService.findUserByEmail(email);

        UsersProfileDto updatedUser = usersService.updateUser(user.getId(), updateUserReq);

        return new ResponseEntity<UsersProfileDto>(updatedUser, HttpStatus.ACCEPTED);
    }

    @PutMapping("/update-profile")
    public ResponseEntity<UsersProfileDto> updateUserProfile(@RequestBody @Valid UsersProfileUpdateDto usersProfileUpdateDto) {



        return new ResponseEntity<UsersProfileDto>(HttpStatus.OK);
    }

    @PutMapping(value = "/update-profile-pic", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UsersProfileDto> updateProfilePicture(HttpServletRequest httpServletRequest, @RequestParam(value = "email", required = true) String email, @ModelAttribute UsersProfilePicUpdateDto usersProfileUpdateDto) throws Exception {


        UsersProfileDto updatedUser = usersService.updateUserProfilePic(usersProfileUpdateDto, email);

        log.info("Uploaded profile pic for user {}.", email);
        return new ResponseEntity<UsersProfileDto>(updatedUser,HttpStatus.OK);
    }

    @PutMapping("/{email}/update-email")
    public ResponseEntity<UsersProfileDto> updateEmail(@RequestParam(value = "email", required = true) String email, @RequestBody @Valid UsersProfileUpdateDto usersProfileUpdateDto) {



        return new ResponseEntity<UsersProfileDto>(HttpStatus.OK);
    }

}
