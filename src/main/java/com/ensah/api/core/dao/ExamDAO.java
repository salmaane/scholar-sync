package com.ensah.api.core.dao;

import com.ensah.api.core.models.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExamDAO extends JpaRepository<Exam, Long> {

    @Query("SELECT DISTINCT e.academicYear FROM Exam e")
    String[] getAcademicYears();

    @Query("SELECT e FROM Exam e WHERE e.academicYear = :academicYear")
    List<Exam> findAllByAcademicYear(String academicYear);
}
