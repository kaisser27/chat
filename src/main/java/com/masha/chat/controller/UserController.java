package com.masha.chat.controller;

import com.masha.chat.model.User;
import com.masha.chat.service.ChatService;
import com.masha.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final ChatService chatService;

    @Autowired
    public UserController(UserService userService, ChatService chatService) {
        this.userService = userService;
        this.chatService = chatService;
    }

    @GetMapping("/user")
    public String getUser(ModelMap modelMap, Authentication authentication){
        String name = authentication.getName();
        User user = (User) userService.loadUserByUsername(name);

        if ("ROLE_ADMIN".equals(user.getRole())){
            user.setChats(chatService.getAllChats());
        }

        modelMap.put("user", user);
        return "user";
    }

    @PostMapping("/user")
    public String setUserName(@ModelAttribute("user") User user, ModelMap modelMap, Authentication authentication){
        String name = authentication.getName();
        User userFromDb = (User) userService.loadUserByUsername(name);
        if (user.getName() != null && !user.getName().isBlank()){
            userFromDb.setName(user.getName());
            userService.saveUser(userFromDb);
        }
        if ("ROLE_ADMIN".equals(userFromDb.getRole())){
            userFromDb.setChats(chatService.getAllChats());
        }

        modelMap.put("user", userFromDb);
        return "user";
    }
}
