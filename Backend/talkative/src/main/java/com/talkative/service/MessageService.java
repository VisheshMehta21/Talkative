package com.talkative.service;

import java.util.List;

import com.talkative.dto.SendMessageRequest;
import com.talkative.entity.Message;
import com.talkative.entity.Users;

public interface MessageService {

	public Message sentMessage(SendMessageRequest req);

	public List<Message> getChatsMessages(Long chatId, Users reqUser);

	public Message findMessageById(Long messageId);

	public void deleteMessage(Long messageId, Users reqUser);

}
