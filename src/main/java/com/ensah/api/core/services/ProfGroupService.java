package com.ensah.api.core.services;

import com.ensah.api.core.dao.GroupDAO;
import com.ensah.api.core.dao.ProfessorDAO;
import com.ensah.api.core.dao.UserDAO;
import com.ensah.api.core.dto.GroupDTO;
import com.ensah.api.core.models.ProfGroup;
import com.ensah.api.core.models.Professor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfGroupService extends GenericServiceImpl<ProfGroup> {

    private final GroupDAO dao;
    private final ProfessorDAO professorDAO;
    public ProfGroupService(GroupDAO dao, ProfessorDAO professorDAO) {
        super(dao);
        this.dao = dao;
        this.professorDAO = professorDAO;
    }

    @Override
    public ProfGroup update(Long id, ProfGroup newGroup) {
        return null;
    }

    public ProfGroup save(GroupDTO groupDTO) {
        ProfGroup profGroup = GroupDTO.toProfGroup(groupDTO);

        List<Professor> professors = new ArrayList<>();

        for (Long profId : groupDTO.getUsers()) {
            professorDAO.findById(profId).ifPresent(professor -> {
                professors.add(professor);
                professor.setGroup(profGroup);
            });
        }

        profGroup.setProfessors(professors);

       return dao.save(profGroup);
    }

    @Override
    public void deleteById(Long id) {
         var group = dao.findById(id);
         if(group.isPresent()) {
             for(Professor prof : group.get().getProfessors()) {
                 prof.setGroup(null);
             }
             dao.delete(group.get());
         }
    }

}
