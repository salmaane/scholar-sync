package com.ensah.api.core.dao;

import com.ensah.api.core.models.Information;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface InformationDAO extends JpaRepository<Information, Long> {

    @Query("""
        SELECT i FROM Information i
        WHERE :currentDate BETWEEN i.semesterStartDate and i.examsWeekStartDate
    """)
    Information getCurrentSemesterInformation(LocalDate currentDate);

}
