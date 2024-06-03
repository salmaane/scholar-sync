package com.ensah.api.core.dto;

import com.ensah.api.core.models.Exam;
import com.ensah.api.core.models.Subject;
import com.ensah.api.core.models.Surveillance;
import com.ensah.api.core.models.enums.ExamSemester;
import com.ensah.api.core.models.enums.ExamSession;
import com.ensah.api.core.models.enums.ExamStartHour;
import com.ensah.api.core.models.enums.ExamType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExamDTO {

    private Long id;
    private ExamSession session;
    private ExamType type;
    private ExamSemester semester;
    private LocalDate date;
    private String academicYear;
    private ExamStartHour startHour;
    private Integer durationMinutes;
    private Integer realDurationMinutes;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long groupId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long subjectId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<ProfsPerClass> classes;
    private List<Surveillance> surveillances;
    private Subject subject;


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProfsPerClass {
        private Long classId;
        private Long number;
    }

    public static ExamDTO toDTO(Exam exam) {
        return ExamDTO.builder()
                .id(exam.getId())
                .session(exam.getSession())
                .type(exam.getType())
                .semester(exam.getSemester())
                .date(exam.getDate())
                .academicYear(exam.getAcademicYear())
                .startHour(exam.getStartHour())
                .durationMinutes(exam.getDurationMinutes())
                .realDurationMinutes(exam.getRealDurationMinutes())
                .surveillances(exam.getSurveillances())
                .subject(exam.getSubject())
                .build();
    }

    public static Exam toExam(ExamDTO examDTO) {
        return  Exam.builder()
                .id(examDTO.id)
                .session(examDTO.getSession())
                .type(examDTO.getType())
                .semester(examDTO.getSemester())
                .date(examDTO.getDate())
                .academicYear(examDTO.getAcademicYear())
                .startHour(examDTO.getStartHour())
                .durationMinutes(examDTO.getDurationMinutes())
                .realDurationMinutes(examDTO.getRealDurationMinutes())
                .build();
    }
}
