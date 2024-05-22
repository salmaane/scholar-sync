package com.ensah.api.core.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Professor extends User{

    private int cin;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Surveillance> surveillances;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "coordinator")
    private Set<Surveillance> managed_surveillances;

    @ManyToOne(fetch = FetchType.EAGER)
    private Department department;

    @ManyToOne(fetch = FetchType.EAGER)
    private Sector sector;

    @ManyToOne(fetch = FetchType.EAGER)
    private ProfGroup group;

}
