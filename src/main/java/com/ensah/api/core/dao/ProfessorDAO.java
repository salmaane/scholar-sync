package com.ensah.api.core.dao;

import com.ensah.api.core.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfessorDAO extends JpaRepository<Professor, Long> {
    @Query("SELECT p FROM Professor p WHERE p.department.id = :departmentId")
    List<Professor> findByDepartmentId(@Param("departmentId") Long departmentId);

    @Query("SELECT p FROM Professor p WHERE p.sector.id = :sectorId")
    List<Professor> findBySectorId(@Param("sectorId") Long sectorId);

}
