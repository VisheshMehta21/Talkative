package com.talkative.service;

import com.talkative.dto.GroupChatReq;
import com.talkative.entity.Chat;
import com.talkative.entity.Users;

public interface ChatService {

    public Chat createChat(Users reqUser, String email);

    public Chat createGroup(GroupChatReq req, Users reqUser);
}
