package com.masha.chat;

import com.masha.chat.model.User;
import com.masha.chat.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatApplication.class, args);
	}

	@Bean
	public ApplicationRunner dataLoader(UserRepository repo, BCryptPasswordEncoder bCryptPasswordEncoder){
		return args -> {
			User user = new User();
			user.setLogin("test");
			user.setPassword(bCryptPasswordEncoder.encode("123"));
			repo.addUser(user);

			var userFromDb = repo.findByLogin("test");
			System.out.println(userFromDb);
		};
	}

}
