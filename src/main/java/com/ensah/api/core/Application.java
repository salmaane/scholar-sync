package com.ensah.api.core;

import com.ensah.api.core.dto.RegisterRequest;
import com.ensah.api.core.models.enums.Role;
import com.ensah.api.core.services.AuthenticationService;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	@Transactional
	public CommandLineRunner initialize(AuthenticationService authService) {
		return args -> {
			var admin = RegisterRequest.builder()
					.firstName("admin")
					.lastName("admin")
					.email("admin@gmail.com")
					.password("admin")
					.role(Role.ADMIN)
					.build();

			System.out.println("ADMIN TOKEN: " + authService.register(admin).getToken());
		};
	}
}
