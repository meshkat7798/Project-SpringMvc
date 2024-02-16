package com.example.projectspringmvc.controller;

import com.example.projectspringmvc.dto.OfferDto;
import com.example.projectspringmvc.dto.response.ResponseOfferDto;
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

    //Done
    @PostMapping("/save-offer")
    public ResponseEntity<OfferDto> save(@RequestBody OfferDto offerDto) {
        return new ResponseEntity<>(offerService.save(offerDto), HttpStatus.OK);
    }


    //Done
    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<ResponseOfferDto> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(offerService.findById(id), HttpStatus.OK);
    }


    //Done
    @GetMapping("/find-all")
    public ResponseEntity<List<ResponseOfferDto>> findAll() {
        return new ResponseEntity<>(offerService.findAll(), HttpStatus.OK);
    }


    //Done
    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<OfferDto> deleteById(@PathVariable("id") int id) {
        return new ResponseEntity<>(offerService.deleteById(id), HttpStatus.OK);
    }


    //Done
    @GetMapping("/existsById/{id}")
    public boolean existsById(@PathVariable("id") Integer id) {
        return offerService.existsById(id);
    }



    //Done
    @GetMapping("/findOfferByOrder/{orderId}")
    public ResponseEntity<List<ResponseOfferDto>> findOfferByOrder(@PathVariable("orderId") int orderId) {
        return new ResponseEntity<>(offerService.findOfferByOrder( orderId), HttpStatus.OK);
    }

    //Done
    @GetMapping("/findAllSortedBySpecialistAverageScore/{orderId}")
    public ResponseEntity<List<ResponseOfferDto>> findAllSortedBySpecialistAverageScore(@PathVariable("orderId") int orderId) {
        return new ResponseEntity<>(offerService.findAllSortedBySpecialistAverageScore(orderId), HttpStatus.OK);
    }

    //Done
    @GetMapping("/findAllSortedByPriceyOrder/{orderId}")
    public ResponseEntity<List<ResponseOfferDto>> findAllSortedByPrice(@PathVariable("orderId") int orderId) {
        return new ResponseEntity<>(offerService.findAllSortedByPrice(orderId), HttpStatus.OK);
    }

}
