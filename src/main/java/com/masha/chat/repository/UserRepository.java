package com.masha.chat.repository;

import com.masha.chat.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//repository anotation
@Component
public class UserRepository {

    private List<User> repo = new ArrayList<>();

    public void addUser(User user){
        repo.add(user);
    }

    public User findByLogin(String login){
        for (var user: repo){
            if (user.getLogin().equals(login))
                return user;
        }
        return null;
    }
}
