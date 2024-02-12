package com.example.projectspringmvc.repository;

import com.example.projectspringmvc.entity.Service;
import com.example.projectspringmvc.entity.SubService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
@Repository
public interface SubServiceRepository extends JpaRepository<SubService, Integer> {
    @Query(value = " FROM SubService i WHERE i.service =?1")
    List<SubService> subServicesOfOneService(Service service);

    @Query(value = "select count(u.name)>0 from SubService u where u.name =?1")
    boolean existByName(String name);

    Optional<SubService> findByName(String username);
}
