package com.ensah.api.core.services;

import com.ensah.api.core.dao.ProfessorDAO;
import com.ensah.api.core.dto.UserDTO;
import com.ensah.api.core.models.Professor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorDAO professorDAO;

    public List<UserDTO> getAllProfessors() {
        List<Professor> professors = professorDAO.findAll();
        return professors.stream().map(UserDTO::toDTO).toList();
    }
    public List<UserDTO> getProfessorsByDepartment(Long departmentId) {
        List<Professor> professors = professorDAO.findByDepartmentId(departmentId);
        return professors.stream().map(UserDTO::toDTO).toList();

    }
    public List<UserDTO> getProfessorsBySector(Long sectorId) {
        List<Professor> professors = professorDAO.findBySectorId(sectorId);
        return professors.stream().map(UserDTO::toDTO).toList();
    }
}
