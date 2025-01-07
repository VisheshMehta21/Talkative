package com.talkative.controller;

import com.talkative.entity.Chat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chats")
public class ChatController {

    @GetMapping("/sayHello")
    public String temp() {
        return "HEllo";
    }

    @PostMapping("/individual")
    public ResponseEntity<Chat> createIndividualChat() {

        
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
