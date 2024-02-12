package com.example.projectspringmvc.repository;

import com.example.projectspringmvc.entity.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@SuppressWarnings("unused")
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query(value = "select count(u.username)>0 from Customer u where u.username = ?1")
    boolean existByUserName(String username);

    @Query(value = "select count(u.email)>0 from Customer u where u.email = ?1")
    boolean existByEmail(String email);

    @Query(value = " from Customer u where u.username = ?1")
    Optional<Customer> findByUserName(String userName);


}
