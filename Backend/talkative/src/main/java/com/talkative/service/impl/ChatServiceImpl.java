package com.talkative.service.impl;

import com.talkative.dto.GroupChatReq;
import com.talkative.entity.Chat;
import com.talkative.entity.Users;
import com.talkative.exception.ChatNotFoundException;
import com.talkative.repository.ChatRepository;
import com.talkative.service.ChatService;
import com.talkative.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ChatServiceImpl implements ChatService {

    @Autowired
    UsersService usersService;

    @Autowired
    ChatRepository chatRepository;

    @Override
    public Chat createChat(Users reqUser, String email) {

        Users user2 = usersService.findUserByEmail(email);

        Chat isChatExist = chatRepository.findSingleChatByUserIds(user2, reqUser);

        if (isChatExist!=null) {
            return isChatExist;
        }

        Chat chat = new Chat();
        chat.setCreatedBy(reqUser);
        chat.getUsers().add(user2);
        chat.getUsers().add(reqUser);
        chat.setGroup(false);
        chatRepository.save(chat);

        return chat;
    }

    @Override
    public Chat createGroup(GroupChatReq req, Users reqUser) {

        Chat group = new Chat();
        group.setGroup(true);
        group.setChatImg(req.getChatImage());
        group.setChatName(req.getChatName());
        group.setCreatedBy(reqUser);
        group.getAdmins().add(reqUser);
        // get member 1 by 1 and add it into grp
        for(String email : req.getEmails()) {
            Users usersToAddGroup = usersService.findUserByEmail(email);
            group.getUsers().add(usersToAddGroup);
        }

        log.info("Creating group requested by {}", reqUser);

        Chat savedChats = chatRepository.save(group);

        log.info("Group saved to Database {}", savedChats);

        return savedChats;
    }

    @Override
    public Chat findChatById(Long chatId) {
        return chatRepository.findById(chatId)
                .orElseThrow(() -> new ChatNotFoundException(String.format("User with Chat Id %s not found.", chatId)));

    }

    @Override
    public List<Chat> findAllChatByUserId(String email) {

        Users user = usersService.findUserByEmail(email);

        return chatRepository.findChatByUserId(user.getId());
    }

    @Override
    public Chat addUserToGroup(Long chatId, String email, Users reqUser) {

        Chat group = chatRepository.findById(chatId)
                .orElseThrow(() -> new ChatNotFoundException(String.format("User with Chat Id %s not found.", chatId)));

        Users user = usersService.findUserByEmail(email);

        if (group.getAdmins().contains(reqUser)) {
            group.getUsers().add(user);
            return chatRepository.save(group);
        }
        else {
            throw new BadCredentialsException("U dont have access to add members in group");
        }
    }

    @Override
    public Chat removeFromGroup(Long chatId, String email, Users reqUser) {

        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new ChatNotFoundException(String.format("User %s with Chat Id %s not found.", email, chatId)));

        Users user = usersService.findUserByEmail(email);

        if (chat.getAdmins().contains(reqUser)) {
            chat.getUsers().remove(user);
            return chatRepository.save(chat);

        }
        else if (chat.getUsers().contains(reqUser)) {
            if(user.getId().equals(reqUser.getId())) {
                chat.getUsers().remove(user);
                return chatRepository.save(chat);
            }
        }

        throw new BadCredentialsException("You Can't remove another user");

    }

    @Override
    public void deleteChat(Long chatId) {

        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new ChatNotFoundException(String.format("Chat with Id %s not found.", chatId)));

        chatRepository.deleteById(chatId);
    }

}
