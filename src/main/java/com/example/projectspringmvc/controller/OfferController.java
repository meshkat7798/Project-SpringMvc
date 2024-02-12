package com.example.projectspringmvc.controller;

import com.example.projectspringmvc.dto.AdminDto;
import com.example.projectspringmvc.dto.OfferDto;
import com.example.projectspringmvc.entity.MyOrder;
import com.example.projectspringmvc.entity.Offer;
import com.example.projectspringmvc.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/offer")
public class OfferController {

    private final OfferService offerService;

    @PostMapping("/save-offer")
    public ResponseEntity<OfferDto> save(@RequestBody OfferDto offerDto) {
        return new ResponseEntity<OfferDto>(offerService.save(offerDto), HttpStatus.OK);
    }


    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<OfferDto> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<OfferDto>(offerService.findById(id), HttpStatus.OK);
    }


    @GetMapping("/find-all")
    public ResponseEntity<List<OfferDto>> findAll() {
        return new ResponseEntity<List<OfferDto>>(offerService.findAll(), HttpStatus.OK);
    }


    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<OfferDto> deleteById(@PathVariable("id") int id) {
        return new ResponseEntity<OfferDto>(offerService.deleteById(id), HttpStatus.OK);
    }

    @GetMapping("/existsById/{id}")
    public boolean existsById(@PathVariable("id") Integer id) {
        return offerService.existsById(id);
    }

    @GetMapping("/hasRightTime")
    public boolean hasRightTime(@RequestBody Offer offer) {

        return offerService.hasRightTime(offer);
    }

    @GetMapping("/hasRightPrice")
    public boolean hasRightPrice(@RequestBody Offer offer) {

        return offerService.hasRightPrice(offer);
    }

    @GetMapping("/findOfferByOrder")
    public ResponseEntity<List<OfferDto>> findOfferByOrder(@RequestBody MyOrder order) {
        return new ResponseEntity<List<OfferDto>>(offerService.findOfferByOrder(order), HttpStatus.OK);
    }

    @GetMapping("/findAllSortedBySpecialistAverageScore")
    public ResponseEntity<List<OfferDto>> findAllSortedBySpecialistAverageScore(@RequestBody MyOrder order) {
        return new ResponseEntity<List<OfferDto>>(offerService.findAllSortedBySpecialistAverageScore(order), HttpStatus.OK);
    }

    @GetMapping("/findOfferBfindAllSortedByPriceyOrder")
    public ResponseEntity<List<OfferDto>> findAllSortedByPrice(@RequestBody MyOrder order) {
        return new ResponseEntity<List<OfferDto>>(offerService.findAllSortedByPrice(order), HttpStatus.OK);
    }

}
