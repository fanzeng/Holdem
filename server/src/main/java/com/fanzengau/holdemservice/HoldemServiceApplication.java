package com.fanzengau.holdemservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import java.util.Arrays;

@SpringBootApplication
public class HoldemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HoldemServiceApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
		};
	}

	@Bean
	public RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory connectionFactory) {
		RedisTemplate<?, ?> template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory);

		return template;
	}
}
