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

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Surveillance> surveillances;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "coordinator")
    private Set<Surveillance> managed_surveillances;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    private Sector sector;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProfGroup group;

}
