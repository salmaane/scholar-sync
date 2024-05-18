package com.ensah.api.core;

import com.ensah.api.core.dao.RoleDAO;
import com.ensah.api.core.dao.UserDAO;
import com.ensah.api.core.models.Role;
import com.ensah.api.core.models.User;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	@Transactional
	public CommandLineRunner initialize(RoleDAO roleDAO, UserDAO userDAO, PasswordEncoder passwordEncoder) {
		return args -> {
			String[] roleNames = {"ROLE_PROF", "ROLE_ADMIN"};

			for (String roleName : roleNames) {
				if (roleDAO.findByName(roleName) == null) {
					Role role = new Role();
					role.setName(roleName);
					roleDAO.save(role);
				}
			}

			Role userRole = roleDAO.findByName("ROLE_ADMIN");

			var user = User.builder()
					.firstName("admin")
					.lastName("admin")
					.email("admin@gmail.com")
					.password(passwordEncoder.encode("admin"))
					.roles(List.of(userRole))
					.build();
			userDAO.save(user);

		};
	}
}
