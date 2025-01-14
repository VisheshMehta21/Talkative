package com.talkative.service;

import java.util.List;

import com.talkative.dto.SendMessageRequest;
import com.talkative.entity.Message;

public interface MessageService {

	public Message sentMessage(SendMessageRequest req);

}
