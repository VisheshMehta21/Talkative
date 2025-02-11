package com.talkative.config;

import com.talkative.entity.Users;
import com.talkative.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
// @Profile("docker") // Runs only in the 'docker' environment
public class DockerDataLoader {

    @Autowired
    private final UsersRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Bean
    public ApplicationRunner initUsers() {
        return args -> {
            if (userRepository.count() == 0) { // Prevent duplicate entries
                System.out.println("Seeding Friends characters in dev environment...");

                LocalDateTime now = LocalDateTime.now(); // Get the current timestamp

                List<Users> users = List.of(
                        new Users(null, "Ross", "Geller", "ross@friends.com", passwordEncoder.encode("friends"),
                                "https://i.imgur.com/abcRoss.png", now, null, false),
                        new Users(null, "Rachel", "Green", "rachel@friends.com", passwordEncoder.encode("friends"),
                                "https://i.imgur.com/abcRachel.png", now, null, false),
                        new Users(null, "Monica", "Geller", "monica@friends.com", passwordEncoder.encode("friends"),
                                "https://i.imgur.com/abcMonica.png", now, null, false),
                        new Users(null, "Chandler", "Bing", "chandler@friends.com", passwordEncoder.encode("friends"),
                                "https://i.imgur.com/abcChandler.png", now, null, false),
                        new Users(null, "Joey", "Tribbiani", "joey@friends.com", passwordEncoder.encode("friends"),
                                "https://i.imgur.com/abcJoey.png", now, null, false),
                        new Users(null, "Phoebe", "Buffay", "phoebe@friends.com", passwordEncoder.encode("friends"),
                                "https://i.imgur.com/abcPhoebe.png", now, null, false)
                );

                userRepository.saveAll(users);
                System.out.println("Users added successfully.");
            }
        };
    }
}
