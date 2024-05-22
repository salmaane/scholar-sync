package com.ensah.api.core.dao;

import com.ensah.api.core.models.ProfGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupDAO extends JpaRepository<ProfGroup, Long> {

}
