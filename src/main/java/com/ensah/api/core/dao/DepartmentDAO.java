package com.ensah.api.core.dao;

import com.ensah.api.core.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentDAO extends JpaRepository<Department, Long> {

}
