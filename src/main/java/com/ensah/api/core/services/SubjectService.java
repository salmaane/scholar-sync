package com.ensah.api.core.services;

import com.ensah.api.core.dao.ProfessorDAO;
import com.ensah.api.core.dao.SubjectDAO;
import com.ensah.api.core.dto.SubjectDTO;
import com.ensah.api.core.models.Professor;
import com.ensah.api.core.models.Subject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService extends GenericServiceImpl<Subject> {

    private final SubjectDAO dao;
    private final ProfessorDAO professorDAO;

    public SubjectService(SubjectDAO dao, ProfessorDAO professorDAO) {
        super(dao);
        this.dao = dao;
        this.professorDAO = professorDAO;
    }

    @Override
    public Subject update(Long id, Subject newSubject) {
        Optional<Subject> subject = findById(id);
        if(subject.isPresent()) {
            newSubject.setId(id);
            return dao.save(newSubject);
        }
        return null;
    }

    public Subject save(SubjectDTO subjectDTO) {
        Subject subject = SubjectDTO.toSubject(subjectDTO);

        Optional<Professor> professor = professorDAO.findById(subjectDTO.getProfessorId());
        Optional<Professor> coordinator = professorDAO.findById(subjectDTO.getCoordinatorId());

        professor.ifPresent(subject::setProfessor);
        coordinator.ifPresent(subject::setCoordinator);

        return dao.save(subject);
    }

}
