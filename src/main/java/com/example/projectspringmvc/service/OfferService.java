package com.example.projectspringmvc.service;

import com.example.projectspringmvc.dto.OfferDto;
import com.example.projectspringmvc.entity.MyOrder;
import com.example.projectspringmvc.entity.Offer;
import java.util.List;


public interface OfferService {

    OfferDto save(OfferDto offerDto);

    OfferDto deleteById(int id);

    OfferDto findById(Integer id);

    List<OfferDto> findAll();

    boolean existsById(Integer id);

    boolean hasRightTime(Offer offer);

    boolean hasRightPrice(Offer offer);

    List<OfferDto> findOfferByOrder(MyOrder order);

    List<OfferDto> findAllSortedBySpecialistAverageScore(MyOrder order);

    List<OfferDto> findAllSortedByPrice(MyOrder order);


}
