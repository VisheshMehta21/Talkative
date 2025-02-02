package com.talkative.service.impl;

import com.talkative.dto.SendMessageRequest;
import com.talkative.entity.Chat;
import com.talkative.entity.Message;
import com.talkative.entity.Users;
import com.talkative.exception.ChatNotFoundException;
import com.talkative.exception.MessageNotFoundException;
import com.talkative.exception.UserException;
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
		message.setChat(chat);
		message.setSenderId(user);
		message.setContent(req.getContent());
		message.setTimestamp(LocalDateTime.now());

        return messageRepository.save(message);
	}

	@Override
	public List<Message> getChatsMessages(Long chatId, Users reqUser) {

		Chat chat = chatService.findChatById(chatId);

		if(!chat.getUsers().contains(reqUser)) {
			throw new ChatNotFoundException(String.format("You are not authorized to view the messages for Chat Id %s.", chat.getChatId()));
		}

        return messageRepository.findByChatId(chat.getChatId());
	}

	@Override
	public Message findMessageById(Long messageId) {

		return messageRepository.findById(messageId).orElseThrow( () ->
				new MessageNotFoundException(String.format("Message with Message Id %s not found.", messageId))
		);
	}

	@Override
	public void deleteMessage(Long messageId, Users reqUser) {

		Message message = findMessageById(messageId);

		if(message.getSenderId().getId().equals(reqUser.getId())) {
			messageRepository.deleteById(messageId);
		}
		else {
			throw new UserException(String.format("You cannot delete another users message %s.", reqUser.getUsername()));
		}
	}

}
