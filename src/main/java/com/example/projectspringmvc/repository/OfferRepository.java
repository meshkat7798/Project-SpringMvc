package com.example.projectspringmvc.repository;


import com.example.projectspringmvc.entity.MyOrder;
import com.example.projectspringmvc.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Integer> {

    @Query(value = "FROM Offer u where u.order =?1 ORDER BY u.offeredPrice DESC ")
    List<Offer> findAllSortedByPrice(MyOrder order);

    @Query(value = "FROM Offer u where u.order =?1 ORDER BY u.specialist.averageScore DESC ")
    List<Offer> findAllSortedBySpecialistAverageScore(MyOrder order);

}
