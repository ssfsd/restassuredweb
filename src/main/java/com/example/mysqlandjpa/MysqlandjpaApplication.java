package com.example.mysqlandjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MysqlandjpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MysqlandjpaApplication.class, args);
	}

}
