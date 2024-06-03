package com.ensah.api.core.dao;

import com.ensah.api.core.models.Professor;
import com.ensah.api.core.models.enums.ExamStartHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ProfessorDAO extends JpaRepository<Professor, Long> {
    @Query("SELECT p FROM Professor p WHERE p.department.id = :departmentId")
    List<Professor> findByDepartmentId(@Param("departmentId") Long departmentId);

    @Query("SELECT p FROM Professor p WHERE p.sector.id = :sectorId")
    List<Professor> findBySectorId(@Param("sectorId") Long sectorId);

    List<Professor> findProfessorsByGroup_Id(Long groupId);

    @Query("""
        SELECT p FROM Professor p WHERE p.group.id = :groupId AND p NOT IN (
            SELECT s.professors FROM Surveillance s
            WHERE s.exam.date = :date and s.exam.startHour = :startHour
        ) AND (
            SELECT COUNT(s) FROM Surveillance s
            WHERE s.exam.date = :date AND p MEMBER OF s.professors
        ) < :surveillanceLimit
    """)
    List<Professor> findAvailableForSurveillance(
            Long groupId, LocalDate date, ExamStartHour startHour, Integer surveillanceLimit
    );

    @Query("""
        SELECT p FROM Professor p WHERE p NOT IN (
            SELECT s.professors FROM Surveillance s
            WHERE s.exam.date = :date and s.exam.startHour = :startHour
        ) AND (
            SELECT COUNT(s) FROM Surveillance s
            WHERE s.exam.date = :date AND p MEMBER OF s.professors
        ) < :surveillanceLimit
    """)
    List<Professor> findRandomAvailableForSurveillance(
            LocalDate date, ExamStartHour startHour, Integer surveillanceLimit
    );

}
