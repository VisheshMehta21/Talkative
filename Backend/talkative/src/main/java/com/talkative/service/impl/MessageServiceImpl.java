package com.talkative.service.impl;

import com.talkative.dto.SendMessageRequest;
import com.talkative.entity.Chat;
import com.talkative.entity.Message;
import com.talkative.entity.Users;
import com.talkative.repository.MessageRepository;
import com.talkative.service.ChatService;
import com.talkative.service.MessageService;
import com.talkative.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	private UsersService usersService;
	@Autowired
	private ChatService chatService;
	
	@Override
	public Message sentMessage(SendMessageRequest req) {
		
		Users user = usersService.findUserById(req.getUserId());

		Chat chat = chatService.findChatById(req.getChatId());
		
		Message message = new Message();
		message.setChatId(chat);
		message.setSenderId(user);
		message.setContent(req.getContent());
		message.setTimestamp(LocalDateTime.now());

        return messageRepository.save(message);
	}

}
