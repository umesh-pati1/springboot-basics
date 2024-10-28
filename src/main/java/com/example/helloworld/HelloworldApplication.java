package com.example.helloworld;

import com.example.helloworld.run.Location;
import com.example.helloworld.run.Run;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;


@RestController
@SpringBootApplication
public class HelloworldApplication {

	private static final Logger log = LoggerFactory.getLogger(HelloworldApplication.class);

	public static void main(String[] args) {

		ConfigurableApplicationContext context =  SpringApplication.run(HelloworldApplication.class, args);
		WelcomeMessage welcomeMessage = (WelcomeMessage) context.getBean("welcomeMessage");

		log.info("HelloWorldApplication started successfully");
		System.out.println(welcomeMessage);
	}

	@Bean
	CommandLineRunner runner(){
		return args -> {
			Run run = new Run(1, "Spring", LocalDateTime.now(), LocalDateTime.now().plusHours(1),2 , Location.OUTDOOR);
			log.info(run.toString());
		};
	}

}
