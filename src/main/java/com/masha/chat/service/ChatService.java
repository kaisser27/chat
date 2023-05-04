package com.masha.chat.service;

import com.masha.chat.model.Chat;
import com.masha.chat.model.Message;
import com.masha.chat.model.User;

import java.util.List;

public interface ChatService {

    Chat addMessageToChat(Message message, Chat chat);

    Chat addMessageToChat(Message message, Long chatId);

    Chat addMessageToChat(Long chatId, Message message, User user);

    Chat getChat(Long id);

    List<Chat> getAllChats();

    Chat deleteMessage(Long messageId);
}
