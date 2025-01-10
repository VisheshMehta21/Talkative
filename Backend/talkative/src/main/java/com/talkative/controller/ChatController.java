package com.talkative.controller;

import com.talkative.dto.GroupChatReq;
import com.talkative.dto.SingleChatRequest;
import com.talkative.entity.Chat;
import com.talkative.entity.Users;
import com.talkative.exception.ChatNotFoundException;
import com.talkative.service.ChatService;
import com.talkative.service.UsersService;
import jdk.jshell.spi.ExecutionControl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chats")
@Slf4j
public class ChatController {

    @Autowired
    UsersService usersService;

    @Autowired
    ChatService chatService;

    @GetMapping("/sayHello")
    public String temp() {
        return "HEllo";
    }

    @PostMapping("/individual")
    public ResponseEntity<Chat> createIndividualChatHandler(@RequestBody SingleChatRequest singleChatRequest, @RequestHeader("Authorization") String jwt) throws ExecutionControl.UserException {
        Users reqUser = usersService.findUserByEmail(jwt);
        Chat chat = chatService.createChat(reqUser, singleChatRequest.getEmail());

        return  new ResponseEntity<Chat>(chat, HttpStatus.OK);
    }

    @PostMapping("/group")
    public ResponseEntity<Chat> createGroupChatHandler(@RequestBody GroupChatReq groupChatReq, @RequestHeader("Authorization") String jwt) {
        log.info("Req Received for create grp");

        System.out.println("Req Received for create grp"+ groupChatReq);
        Users reqUser = usersService.findUserByEmail(jwt);

        Chat chat = chatService.createGroup(groupChatReq, reqUser);

        return  new ResponseEntity<Chat>(chat, HttpStatus.OK);
    }

    @GetMapping("/{chatId}")
    public ResponseEntity<Chat> findChatByIdHandler(@PathVariable Integer chatId, @RequestHeader("Authorization") String jwt) throws ChatNotFoundException {

        Chat chat = chatService.findChatById(chatId);

        return  new ResponseEntity<Chat>(chat, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Chat>> findAllChatsByUserIdHandler(@RequestHeader("Authorization") String jwt) {

        Users reqUser = usersService.findUserByEmail(jwt);

        List<Chat> chat = chatService.findAllChatByUserId(reqUser.getEmail());

        return  new ResponseEntity<List<Chat>>(chat, HttpStatus.OK);
    }
}
