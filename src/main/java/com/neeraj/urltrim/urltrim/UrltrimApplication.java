package com.neeraj.urltrim.urltrim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class UrltrimApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrltrimApplication.class, args);
	}

}
