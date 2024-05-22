package com.ensah.api.core.services;

import com.ensah.api.core.dao.GroupDAO;
import com.ensah.api.core.models.ProfGroup;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ProfGroupService extends GenericServiceImpl<ProfGroup> {

    private final GroupDAO dao;
    public ProfGroupService(GroupDAO dao) {
        super(dao);
        this.dao = dao;
    }

    public ProfGroup update(Long id, ProfGroup newGroup) {
        Optional<ProfGroup> group = findById(id);
        if(group.isPresent()) {
            newGroup.setId(id);
            return dao.save(newGroup);
        }
        return null;
    }
}
