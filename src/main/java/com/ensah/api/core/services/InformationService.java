package com.ensah.api.core.services;

import com.ensah.api.core.dao.InformationDAO;
import com.ensah.api.core.dto.InformationDTO;
import com.ensah.api.core.models.Information;
import com.ensah.api.core.models.enums.ExamSemester;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class InformationService {

    private final InformationDAO dao;

    public InformationDTO getInformation() {
        LocalDate currentDate = LocalDate.now();

        Information information = dao.getCurrentSemesterInformation(currentDate);
        InformationDTO informationDTO = InformationDTO.toDTO(information);

        informationDTO.setAcademicYear(
                getAcademicYear(information.getSemesterStartDate(), informationDTO.getSemester())
        );

        return informationDTO;
    }

    private String getAcademicYear(LocalDate currentDate, ExamSemester semester) {
        String academicYear;
        if (semester == ExamSemester.SPRING) {
            academicYear = currentDate.getYear() - 1 + "/" + currentDate.getYear();
        } else {
            academicYear = currentDate.getYear() + "/" + currentDate.getYear() + 1;
        }
        return academicYear;
    }
}
