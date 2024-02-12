package com.example.projectspringmvc.repository;

import com.example.projectspringmvc.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByUsername(String username);

    boolean existsByUsername(String username);
}