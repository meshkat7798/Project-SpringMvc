package com.example.projectspringmvc.repository;

import com.example.projectspringmvc.entity.user.Admin;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Configuration
@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {

    @Query(value ="select count(u.username)>0 from Admin u where u.username = ?1" )
    boolean existByUserName(String username);


    @Query(value =" from Admin u where u.username = ?1" )
    Optional<Admin> findByUserName(String userName);

}
