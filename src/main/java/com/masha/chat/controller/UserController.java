package com.masha.chat.controller;

import com.masha.chat.model.User;
import com.masha.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String getUser(ModelMap modelMap, Authentication authentication){

        String name = authentication.getName();

        User user = (User) userService.loadUserByUsername(name);

        modelMap.put("user", user);

        return "user";

    }
}
