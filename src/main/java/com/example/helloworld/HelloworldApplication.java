package com.example.helloworld;

import com.example.helloworld.user.User;
import com.example.helloworld.user.UserHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.List;

@SpringBootApplication
public class HelloworldApplication {

	private static final Logger log = LoggerFactory.getLogger(HelloworldApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(HelloworldApplication.class, args);

		log.info("HelloWorldApplication started successfully");
	}

	@Bean
	UserHttpClient userHttpClient(){
		RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com");

		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();

		log.info("Initialize UserHttp client");

		return factory.createClient(UserHttpClient.class);
	}


	@Bean
	CommandLineRunner runner(UserHttpClient client){
		return args -> {
			List<User> users = client.findAll();

			System.out.println(users);

			User user = client.findById(1);

			System.out.printf("User by id 1: %s%n", user);
		};
	}


}
