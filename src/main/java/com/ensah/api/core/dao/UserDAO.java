package com.ensah.api.core.dao;

import com.ensah.api.core.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserDAO extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
}
