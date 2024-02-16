package com.example.projectspringmvc.service;

import com.example.projectspringmvc.dto.OfferDto;
import com.example.projectspringmvc.dto.response.ResponseOfferDto;
import com.example.projectspringmvc.entity.MyOrder;
import com.example.projectspringmvc.entity.Offer;
import java.util.List;


public interface OfferService {

    OfferDto save(OfferDto offerDto);

    OfferDto deleteById(int id);

    ResponseOfferDto findById(Integer id);

    OfferDto findById2(Integer id);

    List<ResponseOfferDto> findAll();

    boolean existsById(Integer id);

    boolean hasRightTime(Offer offer,MyOrder order);

    boolean hasRightPrice(Offer offer,MyOrder order);

    List<ResponseOfferDto> findOfferByOrder(int orderId);

    List<ResponseOfferDto> findAllSortedBySpecialistAverageScore(int orderId);

    List<ResponseOfferDto> findAllSortedByPrice(int orderId);


}
