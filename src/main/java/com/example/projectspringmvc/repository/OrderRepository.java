package com.example.projectspringmvc.repository;

import com.example.projectspringmvc.entity.MyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface OrderRepository extends JpaRepository<MyOrder, Integer> {

}
