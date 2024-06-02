package com.ensah.api.core.models;

import com.ensah.api.core.models.enums.ExamSemester;
import com.ensah.api.core.models.enums.ExamSession;
import com.ensah.api.core.models.enums.ExamStartHour;
import com.ensah.api.core.models.enums.ExamType;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private ExamSession session;

    @Enumerated(value = EnumType.STRING)
    private ExamType type;

    @Enumerated(value = EnumType.STRING)
    private ExamSemester semester;

    private LocalDate date;

    private String academicYear;

    @Enumerated(EnumType.STRING)
    private ExamStartHour startHour;

    private Integer durationMinutes;
    @Nullable
    private Integer realDurationMinutes;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "exam")
    private List<Surveillance> surveillances = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private Subject subject;


}
