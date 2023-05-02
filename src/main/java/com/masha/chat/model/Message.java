package com.masha.chat.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "messages")
@Getter
@Setter
public class Message {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_of_sender")
    private String nameOfSender;

    @Column(name = "text")
    private String text;

    @Column(name = "sending_time")
    private LocalDateTime sendingTime;
}
