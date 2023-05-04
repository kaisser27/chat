package com.masha.chat.controller;

import com.masha.chat.model.Chat;
import com.masha.chat.model.Message;
import com.masha.chat.model.User;
import com.masha.chat.service.ChatService;
import com.masha.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class ChatController {

    private final ChatService chatService;
    private final UserService userService;

    @Autowired
    public ChatController(ChatService chatService, UserService userService) {
        this.chatService = chatService;
        this.userService = userService;
    }

    @GetMapping("chat/{chatId}")
    public String getChat(@PathVariable String chatId, ModelMap modelMap){
        Chat chat = chatService.getChat(Long.valueOf(chatId));
        modelMap.put("chat", chat);
        modelMap.put("message", new Message());
        return "chat";
    }

    @PostMapping("chat/{chatId}/addMessage")
    public String addMessage(@PathVariable String chatId, @ModelAttribute("message") Message message,
                             ModelMap modelMap, Authentication authentication){
        String name = authentication.getName();
        User userFromDb = (User) userService.loadUserByUsername(name);
        Chat chat = chatService.addMessageToChat(Long.valueOf(chatId), message, userFromDb);
        modelMap.put("message", new Message());
        modelMap.put("chat", chat);
        return "redirect:/chat/" + chatId;
    }

    @PostMapping("/chat/deleteMessage/{messageId}")
    public String deleteMessage(@PathVariable String messageId, ModelMap modelMap){
        Chat chat = chatService.deleteMessage(Long.valueOf(messageId));
        modelMap.put("message", new Message());
        modelMap.put("chat", chat);
        return "redirect:/chat/" + chat.getId();
    }
}
