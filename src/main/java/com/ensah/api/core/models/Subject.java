package com.ensah.api.core.models;

import com.ensah.api.core.models.enums.Level;
import com.ensah.api.core.models.enums.SubjectType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @Enumerated(value = EnumType.STRING)
    private SubjectType type;

    @Enumerated(EnumType.STRING)
    private Level level;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private Set<Exam> exams;

    @ManyToOne(fetch = FetchType.EAGER)
    private Professor coordinator;

    @ManyToOne(fetch = FetchType.EAGER)
    private Professor professor;
}
