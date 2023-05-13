package com.masha.chat.service;


import com.masha.chat.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User saveUser(User user);

    List<User> getAll();
}
