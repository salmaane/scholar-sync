package com.ensah.api.core;

import com.ensah.api.core.dao.RoleDAO;
import com.ensah.api.core.models.Role;
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
	public CommandLineRunner initializeRoles(RoleDAO roleDAO) {
		return args -> {
			String[] roleNames = {"ROLE_PROF", "ROLE_ADMIN"};

			for (String roleName : roleNames) {
				if (roleDAO.findByName(roleName) == null) {
					Role role = new Role();
					role.setName(roleName);
					roleDAO.save(role);
				}
			}
		};
	}
}
