package com.ensah.api.core.dao;

import com.ensah.api.core.models.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamDAO extends JpaRepository<Exam, Long> {

}
