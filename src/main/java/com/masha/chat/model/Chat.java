package com.masha.chat.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "chats")
@Getter
@Setter
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "chat_user",
            joinColumns = { @JoinColumn(name = "chat_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") })
    private List<User> users;

    @OneToMany
    @JoinColumn(name = "chat_id")
    private List<Message> messages;


    public void addMessage(Message message){
        if (messages == null)
            messages = new ArrayList<>();
        messages.add(message);
    }
}
