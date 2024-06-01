package com.ensah.api.core.dto;

import com.ensah.api.core.models.Information;
import com.ensah.api.core.models.enums.ExamSemester;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InformationDTO {

    private Long id;
    private ExamSemester semester;
    private LocalDate examsWeekStartDate;
    private LocalDate resitExamsWeekStartDate;
    private LocalDate supervisedTestsWeekStartDate;
    private LocalDate semesterStartDate;
    private Integer profSurveillanceNumberPerDay;
    private String academicYear;

    public static InformationDTO toDTO(Information information) {
        return InformationDTO.builder()
                .id(information.getId())
                .semester(information.getSemester())
                .examsWeekStartDate(information.getExamsWeekStartDate())
                .resitExamsWeekStartDate(information.getResitExamsWeekStartDate())
                .supervisedTestsWeekStartDate(information.getSupervisedTestsWeekStartDate())
                .semesterStartDate(information.getSemesterStartDate())
                .profSurveillanceNumberPerDay(information.getProfSurveillanceNumberPerDay())
                .build();
    }

    public static Information toInformation(InformationDTO informationDTO) {
        return Information.builder()
                .id(informationDTO.getId())
                .semester(informationDTO.getSemester())
                .examsWeekStartDate(informationDTO.getExamsWeekStartDate())
                .resitExamsWeekStartDate(informationDTO.getResitExamsWeekStartDate())
                .supervisedTestsWeekStartDate(informationDTO.getSupervisedTestsWeekStartDate())
                .semesterStartDate(informationDTO.getSemesterStartDate())
                .profSurveillanceNumberPerDay(informationDTO.getProfSurveillanceNumberPerDay())
                .build();
    }
}