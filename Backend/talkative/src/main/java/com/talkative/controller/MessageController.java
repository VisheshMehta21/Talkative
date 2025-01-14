package com.talkative.controller;

import java.util.List;

import com.talkative.dto.SendMessageRequest;
import com.talkative.entity.Message;
import com.talkative.entity.Users;
import com.talkative.service.MessageService;
import com.talkative.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class MessageController {

	@Autowired
	private MessageService messageService;
	@Autowired
	private UsersService usersService;
	
	
	@PostMapping("/create")
	public ResponseEntity<Message> sentMessageHandler(@RequestBody SendMessageRequest sendMessageRequest, @RequestHeader("Authorization") String jwt) {

		Users user = usersService.findUserByEmail(jwt);

		sendMessageRequest.setUserId(user.getId());
		Message sentMessage = messageService.sentMessage(sendMessageRequest);
		System.out.println("Message sent successfully from" + user.getId());

		return new ResponseEntity<Message>(sentMessage, HttpStatus.OK);

	}

}
