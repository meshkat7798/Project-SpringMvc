package com.example.projectspringmvc.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    String name;

    @Column(unique = true,nullable = false)
    String username;

    @Column(nullable = false)
    String password;

}
