package com.ensah.api.core.models;

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

    @ManyToOne(fetch = FetchType.EAGER) // Or Make it Enumeration
    private Level level;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private Set<Exam> exams;

    // Users (ADMIN - PROF) Relations

}
