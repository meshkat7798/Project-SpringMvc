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
public class SubService{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ToString.Exclude
    @ManyToMany
    private List<Specialist> specialists;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Service service;

    @Column(unique = true,nullable = false)
    private String name;

    private double basePrice;

    private String details;

    public SubService(Service service, String name, double basePrice,String details ) {
        this.service = service;
        this.name = name;
        this.basePrice = basePrice;
        this.details = details;
        this.setSpecialists(new ArrayList<>());
    }
}
