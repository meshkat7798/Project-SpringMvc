package com.example.projectspringmvc.controller;

import com.example.projectspringmvc.dto.MyOrderDto;
import com.example.projectspringmvc.dto.response.ResponseOrderDto;
import com.example.projectspringmvc.entity.enumeration.OrderStatus;
import com.example.projectspringmvc.service.impl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderServiceImpl orderService;


    //Done
    @PostMapping("/save-order")
    public ResponseEntity<MyOrderDto> save(@RequestBody MyOrderDto orderDto) {
        return new ResponseEntity<>(orderService.save(orderDto), HttpStatus.OK);
    }


    //Done
    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<ResponseOrderDto> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(orderService.findById(id), HttpStatus.OK);
    }


    //Done
    @GetMapping("/find-all")
    public ResponseEntity<List<ResponseOrderDto>> findAll() {
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }


    //Done
    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<MyOrderDto> deleteById(@PathVariable("id") int id) {
        return new ResponseEntity<>(orderService.deleteById(id), HttpStatus.OK);
    }


    //Done
    @GetMapping("/existsById/{id}")
    public boolean existsById(@PathVariable Integer id) {

        return orderService.existsById(id);
    }


    //Done
    @PutMapping("/chooseAnOfferForOrder/{orderId}/{offerId}")
    public ResponseEntity<ResponseOrderDto> chooseAnOfferForOrder(@PathVariable("orderId") int orderId,
                                                            @PathVariable("offerId") int offerId) {
        return new ResponseEntity<>(orderService.chooseAnOfferForOrder(orderId, offerId), HttpStatus.OK);
    }

    //Done
    @PutMapping
            ("/changeTheOrderStatusAfterCreatingOffer/{offerId}")
    public ResponseEntity<ResponseOrderDto> changeTheOrderStatusAfterCreatingOffer(@PathVariable("offerId") int offerId) {
        return new ResponseEntity<>(orderService.changeTheOrderStatusAfterCreatingOffer(offerId), HttpStatus.OK);
    }


    //Done
    @GetMapping(
            "/findOrderByCustomer/{customerId}")
    public ResponseEntity<List<ResponseOrderDto>> findOrderByCustomer(@PathVariable("customerId") int customerId) {
        return new ResponseEntity<>(orderService.findOrderByCustomer(customerId), HttpStatus.OK);
    }


    //Done
    @GetMapping("/findOrderByOrderStatus/{orderStatus}")
    public ResponseEntity<List<ResponseOrderDto>> findOrderByOrderStatus(@PathVariable("orderStatus") String orderStatus) {
        return new ResponseEntity<>(orderService.findOrderByOrderStatus(OrderStatus.valueOf(orderStatus)), HttpStatus.OK);
    }

}
