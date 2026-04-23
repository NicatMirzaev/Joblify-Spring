package com.nijad.joblify;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class JoblifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(JoblifyApplication.class, args);
	}

	@Bean
	public CommandLineRunner loggingCheck() {
		return args -> {
			log.info("Joblify Application started successfully.");
			log.debug("Debug logging is enabled.");
		};
	}
}
