package com.ensah.api.core.models;

import com.ensah.api.core.models.enums.ExamSemester;
import com.ensah.api.core.models.enums.ExamType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Information {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ExamSemester semester;

    private LocalDate examsWeekStartDate;
    private LocalDate resitExamsWeekStartDate;
    private LocalDate supervisedTestsWeekStartDate;
    private LocalDate semesterStartDate;
    private Integer profSurveillanceNumberPerDay;

}
