package com.example.projectspringmvc.controller;

import com.example.projectspringmvc.dto.OfferDto;
import com.example.projectspringmvc.dto.response.ResponseOfferDto;
import com.example.projectspringmvc.entity.MyOrder;
import com.example.projectspringmvc.entity.Offer;
import com.example.projectspringmvc.service.impl.OfferServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/offer")
public class OfferController {

    private final OfferServiceImpl offerService;

    @PostMapping("/save-offer")
    public ResponseEntity<OfferDto> save(@RequestBody OfferDto offerDto) {
        return new ResponseEntity<OfferDto>(offerService.save(offerDto), HttpStatus.OK);
    }


    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<ResponseOfferDto> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<ResponseOfferDto>(offerService.findById(id), HttpStatus.OK);
    }


    @GetMapping("/find-all")
    public ResponseEntity<List<ResponseOfferDto>> findAll() {
        return new ResponseEntity<List<ResponseOfferDto>>(offerService.findAll(), HttpStatus.OK);
    }


    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<OfferDto> deleteById(@PathVariable("id") int id) {
        return new ResponseEntity<OfferDto>(offerService.deleteById(id), HttpStatus.OK);
    }

    @GetMapping("/existsById/{id}")
    public boolean existsById(@PathVariable("id") Integer id) {
        return offerService.existsById(id);
    }


    @PostMapping("/check-time")
    public boolean hasRightTime(@RequestBody Offer offer, @RequestBody MyOrder order) {
        return offerService.hasRightTime(offer, order);
    }

    @PostMapping("/check-price")
    public boolean hasRightPrice(@RequestBody Offer offer, @RequestBody MyOrder order) {
        return offerService.hasRightPrice(offer, order);
    }

    @GetMapping("/findOfferByOrder")
    public ResponseEntity<List<ResponseOfferDto>> findOfferByOrder(@RequestBody MyOrder order) {
        return new ResponseEntity<List<ResponseOfferDto>>(offerService.findOfferByOrder(order), HttpStatus.OK);
    }

    @GetMapping("/findAllSortedBySpecialistAverageScore")
    public ResponseEntity<List<ResponseOfferDto>> findAllSortedBySpecialistAverageScore(@RequestBody MyOrder order) {
        return new ResponseEntity<List<ResponseOfferDto>>(offerService.findAllSortedBySpecialistAverageScore(order), HttpStatus.OK);
    }

    @GetMapping("/findOfferBfindAllSortedByPriceyOrder")
    public ResponseEntity<List<ResponseOfferDto>> findAllSortedByPrice(@RequestBody MyOrder order) {
        return new ResponseEntity<List<ResponseOfferDto>>(offerService.findAllSortedByPrice(order), HttpStatus.OK);
    }

}
