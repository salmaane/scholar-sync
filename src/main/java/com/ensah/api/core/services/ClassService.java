package com.ensah.api.core.services;

import com.ensah.api.core.dao.ClassDAO;
import com.ensah.api.core.models.Class;
import com.ensah.api.core.models.Department;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
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

}
