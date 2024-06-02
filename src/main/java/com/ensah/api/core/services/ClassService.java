package com.ensah.api.core.services;

import com.ensah.api.core.dao.ClassDAO;
import com.ensah.api.core.dto.ExamDTO;
import com.ensah.api.core.models.Class;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@PreAuthorize("hasAuthority('ADMIN')")
public class ClassService extends GenericServiceImpl<Class> {

    private final ClassDAO classDAO;

    public ClassService(ClassDAO classDAO) {
        super(classDAO);
        this.classDAO = classDAO;
    }

    @Override
    public Class update(Long id, Class newClass) {
        Optional<Class> oldClass = findById(id);
        if(oldClass.isPresent()) {
            newClass.setId(id);
            return classDAO.save(newClass);
        }
        return null;
    }

    public List<Class> availableClasses(ExamDTO examDTO) {
        return classDAO.getAvailableClasses(examDTO.getDate(), examDTO.getStartHour());
    }

    public List<Class> findAllById(List<Long> ids) {
        return classDAO.findAllById(ids);
    }
}
