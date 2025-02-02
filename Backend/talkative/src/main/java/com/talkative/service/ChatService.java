package com.talkative.service;

import com.talkative.dto.GroupChatReq;
import com.talkative.entity.Chat;
import com.talkative.entity.Users;

import java.util.List;

public interface ChatService {

    public Chat createChat(Users reqUser, String email);

    public Chat createGroup(GroupChatReq req, Users reqUser, String chatImae);

    public Chat findChatById(Long chatId);

    public List<Chat> findAllChatByUserId(String email);

    public Chat addUserToGroup(Long chatId, String email, Users reqUser);

    public Chat removeFromGroup(Long chatId, String email, Users reqUser);

    public void deleteChat(Long chatId);

}
