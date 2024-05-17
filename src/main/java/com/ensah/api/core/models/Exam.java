package com.ensah.api.core.models;

import com.ensah.api.core.helpers.ExamSemester;
import com.ensah.api.core.helpers.ExamSession;
import com.ensah.api.core.helpers.ExamType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@Entity
@Data
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
    private Date date;
    private int durationMinutes;
    private int realDurationMinutes;
    @Enumerated(value = EnumType.STRING)
    private ExamSemester semester;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Surveillance> surveillances;

    @OneToOne(fetch = FetchType.EAGER)
    private Subject subject;


}
