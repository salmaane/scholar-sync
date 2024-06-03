package com.ensah.api.core.services;

import com.ensah.api.core.dao.AdminDAO;
import com.ensah.api.core.models.Administrator;
import com.ensah.api.core.models.enums.ExamStartHour;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminDAO adminDAO;

    public List<Administrator> findAvailableForSurveillance(
            LocalDate date, ExamStartHour startHour, Integer surveillanceLimit
    ) {
        return adminDAO.findAvailableForSurveillance(date, startHour, surveillanceLimit);
    }

    public Long rowsNumber(){
        return adminDAO.count();
    }
}
