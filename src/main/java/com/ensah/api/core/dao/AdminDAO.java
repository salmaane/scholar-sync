package com.ensah.api.core.dao;

import com.ensah.api.core.models.Administrator;
import com.ensah.api.core.models.enums.ExamStartHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface AdminDAO extends JpaRepository<Administrator, Long> {

    @Query("""
        SELECT a FROM Administrator a WHERE a NOT IN (
            SELECT s.absController FROM Surveillance s
            WHERE s.exam.date = :date and s.exam.startHour = :startHour
        ) AND (
            SELECT COUNT(s) FROM Surveillance s
            WHERE s.exam.date = :date AND a = s.absController
        ) < :surveillanceLimit
    """)
    List<Administrator> findAvailableForSurveillance(
            LocalDate date, ExamStartHour startHour, Integer surveillanceLimit
    );
}
