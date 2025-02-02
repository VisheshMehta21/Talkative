package com.talkative.controller;

import java.util.List;

import com.talkative.apiresponse.ApiResponse;
import com.talkative.dto.SendMessageRequest;
import com.talkative.entity.Message;
import com.talkative.entity.Users;
import com.talkative.service.MessageService;
import com.talkative.service.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	public ResponseEntity<Message> sentMessageHandler(HttpServletRequest httpServletRequest, @RequestBody SendMessageRequest sendMessageRequest) {

		// Get the authentication from the security context
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		// Extract email from the authentication (after JwtAuthenticationFilter sets it)
		String email = authentication.getName();

		Users user = usersService.findUserByEmail(email);

		sendMessageRequest.setUserId(user.getId());
		Message sentMessage = messageService.sentMessage(sendMessageRequest);
		System.out.println("Message sent successfully from" + user.getId());

		return new ResponseEntity<Message>(sentMessage, HttpStatus.OK);

	}

	@GetMapping("/chat/{chatId}")
	public ResponseEntity<List<Message>> GetChatMessagesHandler(@PathVariable Long chatId, HttpServletRequest httpServletRequest) {

		// Get the authentication from the security context
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		// Extract email from the authentication (after JwtAuthenticationFilter sets it)
		String email = authentication.getName();

		Users user = usersService.findUserByEmail(email);

		List<Message> messages = messageService.getChatsMessages(chatId, user);

		return new ResponseEntity<List<Message>>(messages, HttpStatus.OK);

	}

	@DeleteMapping("/{messageId}")
	public ResponseEntity<ApiResponse> deleteMessagesHandler(@PathVariable Long messageId, @RequestHeader("Authorization") String jwt) {

		Users user = usersService.findUserFromToken(jwt);

		messageService.deleteMessage(messageId, user);

		ApiResponse apiResponse = new ApiResponse("Message deteted succesfuly -----", true);

		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);

	}

}
