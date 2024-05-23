package com.ensah.api.core.dao;

import com.ensah.api.core.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectDAO extends JpaRepository<Subject, Long> {

}
