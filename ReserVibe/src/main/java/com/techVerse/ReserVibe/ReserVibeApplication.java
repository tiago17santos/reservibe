package com.techVerse.ReserVibe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ReserVibeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReserVibeApplication.class, args);
	}

}
