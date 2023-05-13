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
			User user1 = new User();
			user1.setLogin("user1");
			user1.setName("John");
			user1.setRole("ROLE_USER");
			user1.setPassword(bCryptPasswordEncoder.encode("123"));
			repo.save(user1);

			User user2 = new User();
			user2.setLogin("user2");
			user2.setName("Ivan");
			user2.setRole("ROLE_USER");
			user2.setPassword(bCryptPasswordEncoder.encode("123"));
			repo.save(user2);

			User user3 = new User();
			user3.setLogin("user3");
			user3.setName("Jane");
			user3.setRole("ROLE_USER");
			user3.setPassword(bCryptPasswordEncoder.encode("123"));
			repo.save(user3);

			User user4 = new User();
			user4.setLogin("user4");
			user4.setName("Kate");
			user4.setRole("ROLE_USER");
			user4.setPassword(bCryptPasswordEncoder.encode("123"));
			repo.save(user4);

			User admin = new User();
			admin.setLogin("admin");
			admin.setName("admin");
			admin.setRole("ROLE_ADMIN");
			admin.setPassword(bCryptPasswordEncoder.encode("123"));
			repo.save(admin);

			Chat chat1 = new Chat();
			chat1.setName("user1 with user2");
			chat1.setUsers(List.of(user1, user2));
			chatRepository.save(chat1);

			Chat chat2 = new Chat();
			chat2.setName("user1 with user3");
			chat2.setUsers(List.of(user1, user3));
			chatRepository.save(chat2);

			Chat chat3 = new Chat();
			chat3.setName("user2 with user4");
			chat3.setUsers(List.of(user2, user4));
			chatRepository.save(chat3);

		};
	}

}
