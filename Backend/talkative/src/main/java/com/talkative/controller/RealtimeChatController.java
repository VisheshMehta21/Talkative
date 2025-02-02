package com.talkative.controller;

import com.talkative.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Controller
public class RealtimeChatController {
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@MessageMapping("/message")
	@SendTo("/group/public")
	public Message receiveMessage(@Payload Message message) {

		log.info("Sending real time message for Chat Id {}, sent by user {}.",message.getChat().getChatId(), message.getSenderId().getEmail());

		simpMessagingTemplate.convertAndSend("/group/"+message.getChat().getChatId(), message);
		return message;
	}

	@MessageMapping("/test")
	@SendTo("/group/public")
	public String receiveMessageTest(@Payload String message) {

		log.info("I AM RECEIVING MESSAGE");
		return message;
	}
	
}
