package com.example.test.canvas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.example.test.*"})
@EnableJpaRepositories(basePackages = {"com.example.test.canvas.repository"})
@EntityScan("com.example.test.canvas.entity.*")
public class CanvasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CanvasApplication.class, args);
	}

}
