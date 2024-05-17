package com.ensah.api.core.dao;

import com.ensah.api.core.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDAO extends JpaRepository<Role, Long> {
    Role findByName(String name);

}
