package com.ensah.api.core.services;

import com.ensah.api.core.dao.SubjectDAO;
import com.ensah.api.core.models.Sector;
import com.ensah.api.core.models.Subject;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubjectService extends GenericServiceImpl<Subject> {

    private final SubjectDAO dao;

    public SubjectService(SubjectDAO dao) {
        super(dao);
        this.dao = dao;
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


}
