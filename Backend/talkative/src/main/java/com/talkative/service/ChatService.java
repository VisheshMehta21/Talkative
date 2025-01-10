package com.talkative.service;

import com.talkative.dto.GroupChatReq;
import com.talkative.entity.Chat;
import com.talkative.entity.Users;
import com.talkative.exception.ChatNotFoundException;

import java.util.List;

public interface ChatService {

    public Chat createChat(Users reqUser, String email);

    public Chat createGroup(GroupChatReq req, Users reqUser);

    public Chat findChatById(Integer chatId);

    public List<Chat> findAllChatByUserId(String email);
}
