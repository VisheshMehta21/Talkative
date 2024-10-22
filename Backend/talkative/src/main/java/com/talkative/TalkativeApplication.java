package com.talkative;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TalkativeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TalkativeApplication.class, args);
	}

}
