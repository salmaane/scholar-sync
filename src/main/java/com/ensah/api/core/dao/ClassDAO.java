package com.ensah.api.core.dao;

import com.ensah.api.core.models.Class;
import com.ensah.api.core.models.enums.ExamStartHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ClassDAO extends JpaRepository<Class, Long> {

    @Query("""
        SELECT c FROM Class c WHERE c NOT IN (
            SELECT s.aClass FROM Surveillance s
            WHERE s.exam.date = :date AND s.exam.startHour = :startHour
        )
    """)
    List<Class> getAvailableClasses(LocalDate date, ExamStartHour startHour);
}
