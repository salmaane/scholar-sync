package com.ensah.api.core.services;

import com.ensah.api.core.dao.DepartmentDAO;
import com.ensah.api.core.models.Department;
import com.ensah.api.core.models.Sector;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService extends GenericServiceImpl<Department> {

    DepartmentDAO dao;
    public DepartmentService(DepartmentDAO dao) {
        super(dao);
        this.dao = dao;
    }

    public Department update(Long id, Department newDepartment) {
        Optional<Department> sector = findById(id);
        if(sector.isPresent()) {
            newDepartment.setId(id);
            return dao.save(newDepartment);
        }
        return null;
    }
}
