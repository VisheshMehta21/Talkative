package com.talkative.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talkative.apiresponse.ApiResponse;
import com.talkative.dto.GroupChatReq;
import com.talkative.dto.SingleChatRequest;
import com.talkative.entity.Chat;
import com.talkative.entity.Users;
import com.talkative.exception.ChatNotFoundException;
import com.talkative.service.ChatService;
import com.talkative.service.FileStorageService;
import com.talkative.service.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/chats")
@Slf4j
public class ChatController {

    @Autowired
    UsersService usersService;

    @Autowired
    ChatService chatService;

    @Autowired
    FileStorageService fileStorageService;

    @GetMapping("/sayHello")
    public String temp() {
        return "HEllo";
    }

    @PostMapping("/individual")
    public ResponseEntity<Chat> createIndividualChatHandler(HttpServletRequest httpServletRequest, @RequestBody SingleChatRequest singleChatRequest) {

        // Get the authentication from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Extract email from the authentication (after JwtAuthenticationFilter sets it)
        String email = authentication.getName();

        Users reqUser = usersService.findUserByEmail(email);
        Chat chat = chatService.createChat(reqUser, singleChatRequest.getEmail());

        return  new ResponseEntity<Chat>(chat, HttpStatus.OK);
    }

    @PostMapping("/group")
    public ResponseEntity<Chat> createGroupChatHandler(
            @RequestPart("groupChatReq") String groupChatReqJson,  // Receive as JSON string
            @RequestPart(value = "chatImage", required = false) MultipartFile chatImage,  // Handle image upload
            HttpServletRequest httpServletRequest) throws Exception {

        log.info("Req Received for create grp with users {}.", groupChatReqJson);

        // Deserialize the groupChatReq JSON string into a GroupChatReq object
        ObjectMapper objectMapper = new ObjectMapper();
        GroupChatReq groupChatReq = objectMapper.readValue(groupChatReqJson, GroupChatReq.class);

        // Get the authentication from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Users reqUser = usersService.findUserByEmail(email);

        // Handle file upload if it exists
        String groupImageUrl = null;
        if (chatImage != null && !chatImage.isEmpty()) {
            // Upload the file to MinIO and get the URL
            groupImageUrl = fileStorageService.uploadFile(chatImage);  // You'll need to implement the file upload logic in minioService
        }

        // Create the group chat, passing the group image URL if available
        Chat chat = chatService.createGroup(groupChatReq, reqUser, groupImageUrl);

        return new ResponseEntity<>(chat, HttpStatus.OK);
    }



    @GetMapping("/{chatId}")
    public ResponseEntity<Chat> findChatByIdHandler(@PathVariable Long chatId, HttpServletRequest httpServletRequest) throws ChatNotFoundException {

        Chat chat = chatService.findChatById(chatId);

        return  new ResponseEntity<Chat>(chat, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Chat>> findAllChatsByUserIdHandler(HttpServletRequest httpServletRequest) {

        // Get the authentication from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Extract email from the authentication (after JwtAuthenticationFilter sets it)
        String email = authentication.getName();

        List<Chat> chat = chatService.findAllChatByUserId(email);

        return  new ResponseEntity<List<Chat>>(chat, HttpStatus.OK);
    }

    @PutMapping("/{chatId}/add/{userId}")
    public ResponseEntity<Chat> addUserToGroupHandler(@PathVariable Long chatId, @PathVariable String email, @RequestHeader("Authorization") String jwt) {

        Users reqUser = usersService.findUserByEmail(jwt);

        Chat chat = chatService.addUserToGroup(chatId, email, reqUser);

        return  new ResponseEntity<Chat>(chat, HttpStatus.OK);
    }

    @PutMapping("/{chatId}/remove/{userId}")
    public ResponseEntity<Chat> removeUserFromGroupHandler(@PathVariable Long chatId, @PathVariable String email, @RequestHeader("Authorization") String jwt) {

        Users reqUser = usersService.findUserByEmail(jwt);
        Chat chat = chatService.removeFromGroup(chatId, email, reqUser);

        return  new ResponseEntity<Chat>(chat, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{chatId}")
    public ResponseEntity<ApiResponse> deleteChatHandler(@PathVariable Long chatId, @RequestHeader("Authorization") String jwt) {

        Users reqUser = usersService.findUserByEmail(jwt);

        chatService.deleteChat(chatId);

        ApiResponse apiResponse = new ApiResponse("Chat Deleted Succesfully", true);

        return  new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }
}
