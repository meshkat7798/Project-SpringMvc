package com.example.projectspringmvc.repository;


import com.example.projectspringmvc.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@SuppressWarnings("unused")
@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {

    @Query(value = "select count(u.name)>0 from Service u where u.name =?1")
    boolean existByName(String name);

    Optional<Service> findByName(String username);

}
