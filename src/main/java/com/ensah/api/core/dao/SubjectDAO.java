package com.ensah.api.core.dao;

import com.ensah.api.core.models.Subject;
import com.ensah.api.core.models.enums.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubjectDAO extends JpaRepository<Subject, Long> {
    @Query("SELECT s FROM Subject s WHERE s.professor.sector.id = :sectorId AND s.level = :level")
    List<Subject> findBySectorIdAndLevel(Long sectorId, Level level);
}
