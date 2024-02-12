package com.example.projectspringmvc.service.impl;

import com.example.projectspringmvc.dto.OfferDto;
import com.example.projectspringmvc.entity.MyOrder;
import com.example.projectspringmvc.entity.Offer;
import com.example.projectspringmvc.exception.NotFoundException;
import com.example.projectspringmvc.repository.OfferRepository;
import com.example.projectspringmvc.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Configurable
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {


    private final OfferRepository offerRepository;

    private final ModelMapper modelMapper;


    @Override
    public OfferDto save(OfferDto offerDto) {
        Offer offer = modelMapper.map(offerDto, Offer.class);
        if (hasRightPrice(offer) && hasRightTime(offer)) {
            offer = offerRepository.save(offer);
            offerDto = modelMapper.map(offer, OfferDto.class);
            return offerDto;
        }
        throw new NullPointerException("Wrong time Or Price Below basePrice!");
    }

    @Override
    public OfferDto findById(Integer id) {
        Offer offer = offerRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("%d not Fount", id)));
        return modelMapper.map(offer, OfferDto.class);
    }

    @Override
    public OfferDto deleteById(int id) {
        Offer offer = offerRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("%d not Fount", id)));
        OfferDto offerDto = modelMapper.map(offer, OfferDto.class);
        offerRepository.deleteById(id);
        return offerDto;

    }


    @Override
    public boolean existsById(Integer id) {
        return offerRepository.existsById(id);
    }


    @Override
    public boolean hasRightTime(Offer offer) {

        return offer.getOfferedStartingDate().isAfter(offer.getOrder().getDateOfNeed());
    }

    @Override
    public boolean hasRightPrice(Offer offer) {

        return offer.getOfferedPrice() >= offer.getOrder().getSubService().getBasePrice();
    }

    @Override
    public List<OfferDto> findAll() {
        List<Offer> offerList = offerRepository.findAll();
        return offerList.stream().map(offer -> modelMapper
                .map(offer, OfferDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<OfferDto> findOfferByOrder(MyOrder order) {
        List<Offer> offerList = offerRepository.findAll();
        List<Offer> orderOffers = new ArrayList<>();
        for (Offer offer : offerList
        ) {
            if (offer.getOrder().getId().equals(order.getId())) {
                orderOffers.add(offer);
            }

        }
        return offerList.stream().map(offer -> modelMapper
                .map(offer, OfferDto.class)).collect(Collectors.toList());

    }

    @Override
    public List<OfferDto> findAllSortedBySpecialistAverageScore(MyOrder order) {
        List<Offer> offerList = offerRepository.findAllSortedBySpecialistAverageScore(order);

        return offerList.stream().map(offer -> modelMapper
                .map(offer, OfferDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<OfferDto> findAllSortedByPrice(MyOrder order) {
        List<Offer> offerList = offerRepository.findAllSortedByPrice(order);

        return offerList.stream().map(offer -> modelMapper
                .map(offer, OfferDto.class)).collect(Collectors.toList());

    }

}