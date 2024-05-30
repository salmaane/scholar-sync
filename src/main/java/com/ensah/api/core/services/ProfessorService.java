package com.ensah.api.core.services;

import com.ensah.api.core.dao.ProfessorDAO;
import com.ensah.api.core.models.Professor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorDAO professorDAO;

    public List<Professor> getAllProfessors() {
        return professorDAO.findAll();
    }
    public List<Professor> getProfessorsByDepartment(Long departmentId) {
        return professorDAO.findByDepartmentId(departmentId);
    }
    public List<Professor> getProfessorsBySector(Long sectorId) {
        return professorDAO.findBySectorId(sectorId);
    }
}
