package com.masha.chat.service.impl;

import com.masha.chat.model.Chat;
import com.masha.chat.model.Message;
import com.masha.chat.model.User;
import com.masha.chat.repository.ChatRepository;
import com.masha.chat.repository.MessageRepository;
import com.masha.chat.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public ChatServiceImpl(ChatRepository chatRepository, MessageRepository messageRepository) {
        this.chatRepository = chatRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public Chat addMessageToChat(Message message, Chat chat) {
        message.setSendingTime(LocalDateTime.now());
        chat.addMessage(message);
        messageRepository.save(message);
        chatRepository.save(chat);
        return chat;
    }

    @Override
    public Chat addMessageToChat(Message message, Long chatId) {
        Chat chat = chatRepository.findById(chatId).orElseThrow(() -> new RuntimeException("Chat not found"));
        return addMessageToChat(message, chat);
    }

    @Override
    public List<Chat> getAllChats() {
        return chatRepository.findAll();
    }

    @Override
    public Chat getChat(Long id) {
        Chat chat =  chatRepository.findById(id).orElseThrow(() -> new RuntimeException("Chat not fond"));
        chat.setMessages(chat.getMessages()
                .stream()
                .filter(message -> !Boolean.TRUE.equals(message.getIsHidden()))
                .collect(Collectors.toList()));
        return chat;
    }

    @Override
    public Chat addMessageToChat(Long chatId, Message message, User user) {
        message.setNameOfSender(user.getName());
        return addMessageToChat(message, chatId);
    }

    @Override
    public Chat deleteMessage(Long messageId) {
        Message message = messageRepository.findById(messageId).orElseThrow(() -> new RuntimeException("Message not found"));
        message.setIsHidden(true);
        messageRepository.save(message);
        return message.getChat();
    }
}
