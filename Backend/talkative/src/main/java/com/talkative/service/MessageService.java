package com.talkative.service;

import java.util.List;

import com.talkative.dto.SendMessageRequest;
import com.talkative.entity.Message;
import com.talkative.entity.Users;

public interface MessageService {

	public Message sentMessage(SendMessageRequest req);

	public List<Message> getChatsMessages(Integer chatId, Users reqUser);

}
