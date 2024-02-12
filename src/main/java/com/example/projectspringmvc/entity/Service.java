package com.example.projectspringmvc.entity;

import com.example.projectspringmvc.entity.user.Specialist;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuppressWarnings("unused")
public class Service{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ToString.Exclude
    @OneToMany(mappedBy = "service", cascade = CascadeType.MERGE)
    private List<SubService> subServices;

    @Column(unique = true,nullable = false)
    private String name;

    @ToString.Exclude
    @ManyToMany
    private List<Specialist> specialists;

    public Service(String name) {
        this.name = name;
    }

    public Service(int id, String existingServiceName) {
        this.id = id;
        this.name=name;
        this.setSpecialists(new ArrayList<>());
    }
}
