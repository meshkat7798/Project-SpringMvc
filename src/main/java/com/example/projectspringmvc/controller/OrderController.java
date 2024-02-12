package com.example.projectspringmvc.controller;

import com.example.projectspringmvc.dto.MyOrderDto;
import com.example.projectspringmvc.entity.MyOrder;
import com.example.projectspringmvc.entity.enumeration.OrderStatus;
import com.example.projectspringmvc.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class OrderController {

    private final OrderService orderService;


    @PostMapping("/save-order")
    public ResponseEntity<MyOrderDto> save(@RequestBody MyOrderDto orderDto) {
        return new ResponseEntity<MyOrderDto>(orderService.save(orderDto), HttpStatus.OK);
    }


    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<MyOrderDto> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<MyOrderDto>(orderService.findById(id), HttpStatus.OK);
    }


    @GetMapping("/find-all")
    public ResponseEntity<List<MyOrderDto>> findAll() {
        return new ResponseEntity<List<MyOrderDto>>(orderService.findAll(), HttpStatus.OK);
    }


    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<MyOrderDto> deleteById(@PathVariable("id") int id) {
        return new ResponseEntity<MyOrderDto>(orderService.deleteById(id), HttpStatus.OK);
    }


    @GetMapping("/existsById/{id}")
    public boolean existsById(@PathVariable Integer id) {

        return orderService.existsById(id);
    }

    @PutMapping("/chooseAnOfferForOrder/{orderId}/{offerId}")
    public ResponseEntity<MyOrderDto> chooseAnOfferForOrder(@PathVariable("orderId") int orderId,
                                                            @PathVariable("offerId") int offerId,
                                                            @RequestBody MyOrderDto orderDto) {
        orderDto.setId(orderId);
        return new ResponseEntity<MyOrderDto>(orderService.chooseAnOfferForOrder(orderId, offerId), HttpStatus.OK);
    }

    @PutMapping("/changeTheOrderStatusAfterCreatingOffer/{offerId}")
    public ResponseEntity<MyOrderDto> changeTheOrderStatusAfterCreatingOffer(@PathVariable("offerId") int offerId,
                                                                             @RequestBody MyOrderDto orderDto) {
        orderDto.setId(orderDto.getId());
        return new ResponseEntity<MyOrderDto>(orderService.changeTheOrderStatusAfterCreatingOffer(offerId), HttpStatus.OK);
    }

    @GetMapping("/hasRightTime")
    public boolean hasRightTime(@RequestBody MyOrder order) {

        return orderService.hasRightTime(order);
    }

    @GetMapping("/hasRightPrice")
    public boolean hasRightPrice(@RequestBody MyOrder order) {

        return orderService.hasRightPrice(order);
    }

    @GetMapping("/findOrderByCustomer/{customerId}")
    public ResponseEntity<List<MyOrderDto>> findOrderByCustomer(@PathVariable("customerId") int customerId ,@RequestBody MyOrder order) {
        return new ResponseEntity<List<MyOrderDto>>(orderService.findOrderByCustomer(customerId), HttpStatus.OK);
    }


    @GetMapping("/findOrderByOrderStatus/{orderStatus}")
    public ResponseEntity<List<MyOrderDto>> findOrderByOrderStatus(@PathVariable("orderStatus") String orderStatus ,@RequestBody MyOrder order) {
        return new ResponseEntity<List<MyOrderDto>>(orderService.findOrderByOrderStatus(OrderStatus.valueOf(orderStatus)), HttpStatus.OK);
    }

}
