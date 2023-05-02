package com.masha.chat;

import com.masha.chat.model.Chat;
import com.masha.chat.model.User;
import com.masha.chat.repository.ChatRepository;
import com.masha.chat.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootApplication
public class ChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatApplication.class, args);
	}

	@Bean
	public ApplicationRunner dataLoader(UserRepository repo, ChatRepository chatRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
		return args -> {
//			User user1 = new User();
//			user1.setLogin("test");
//			user1.setName("Vasya");
//			user1.setPassword(bCryptPasswordEncoder.encode("123"));
//			repo.save(user1);
//
//			User user2 = new User();
//			user2.setLogin("test2");
//			user2.setName("Petya");
//			user2.setPassword(bCryptPasswordEncoder.encode("123"));
//			repo.save(user2);
//
//			Chat chat = new Chat();
//			chat.setUsers(List.of(user1, user2));
//			chatRepository.save(chat);

		};
	}

}
