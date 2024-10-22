package com.talkative.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @GetMapping("/sayHello")
    public String temp() {
        return "HEllo";
    }
}
