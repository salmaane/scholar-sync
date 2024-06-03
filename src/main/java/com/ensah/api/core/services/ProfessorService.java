package com.ensah.api.core.services;

import com.ensah.api.core.dao.ProfessorDAO;
import com.ensah.api.core.dto.UserDTO;
import com.ensah.api.core.models.Professor;
import com.ensah.api.core.models.enums.ExamStartHour;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorDAO professorDAO;

    public Long rowsNumber(){
        return professorDAO.count();
    }

    public List<UserDTO> getAll() {
        List<Professor> professors = professorDAO.findAll();
        return professors.stream().map(UserDTO::toDTO).toList();
    }
    public List<UserDTO> getByDepartment(Long departmentId) {
        List<Professor> professors = professorDAO.findByDepartmentId(departmentId);
        return professors.stream().map(UserDTO::toDTO).toList();

    }
    public List<UserDTO> getBySector(Long sectorId) {
        List<Professor> professors = professorDAO.findBySectorId(sectorId);
        return professors.stream().map(UserDTO::toDTO).toList();
    }

    public List<Professor> getByGroup(Long groupId) {
        if(groupId == 0) {
            return professorDAO.findAll();
        } else {
            return professorDAO.findProfessorsByGroup_Id(groupId);
        }
    }

    public List<Professor> findAvailableForSurveillance(
            Long groupId, LocalDate date, ExamStartHour startHour, Integer surveillanceLimit
    ){
        return professorDAO.findAvailableForSurveillance(groupId, date, startHour, surveillanceLimit);
    }
}
