package com.ensah.api.core.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Surveillance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Class aClass;

    @ManyToOne(fetch = FetchType.EAGER)
    private Exam exam;

    // Users Relations
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "surveillances")
    private Set<Professor> professors;

    @ManyToOne(fetch = FetchType.EAGER)
    private Professor coordinator;

    @ManyToOne(fetch = FetchType.EAGER)
    private Administrator absController;
}
