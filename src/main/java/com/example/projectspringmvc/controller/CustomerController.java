package com.example.projectspringmvc.controller;

import com.example.projectspringmvc.dto.CustomerDto;
import com.example.projectspringmvc.dto.MyOrderDto;
import com.example.projectspringmvc.dto.response.ResponseCustomerDto;
import com.example.projectspringmvc.dto.response.ResponseOrderDto;
import com.example.projectspringmvc.service.impl.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerServiceImpl customerService;

    //Done
    @PostMapping("/save-user")
    public ResponseEntity<CustomerDto> save(@RequestBody CustomerDto user) {
        return new ResponseEntity<>(customerService.save(user), HttpStatus.OK);
    }

    //Done
    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<ResponseCustomerDto> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(customerService.findById(id), HttpStatus.OK);
    }

    //Done
    @GetMapping("/find-by-username/{username}")
    public ResponseEntity<ResponseCustomerDto> findByUsername(@PathVariable("username") String username) {
        return new ResponseEntity<>(customerService.findByUserName(username), HttpStatus.OK);
    }



    //Done
    @GetMapping("/find-all")
    public ResponseEntity<List<ResponseCustomerDto>> findAll() {
        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
    }


    //Done
    @PutMapping("/update-user/{id}")
    public ResponseEntity<ResponseCustomerDto> update(@PathVariable("id") int id, @RequestBody ResponseCustomerDto customerDto) {
        customerDto.setId(id);
        return new ResponseEntity<>(customerService.update(customerDto), HttpStatus.OK);

    }

    //Done
    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<CustomerDto> deleteById(@PathVariable("id") int id) {
        return new ResponseEntity<>(customerService.deleteById(id), HttpStatus.OK);
    }


    //Done
    @GetMapping("/existsById/{id}")
    public boolean existsById(@PathVariable("id") Integer id) {
        return customerService.existsById(id);
    }

    //Done
    @GetMapping("/existByUserName/{username}")
    public boolean existByUserName(@PathVariable("username") String username) {
        return customerService.existByUserName(username);
    }

    //Done
    @GetMapping("/existByEmail/{email}")
    public boolean existByEmail(@PathVariable("email") String email) {
        return customerService.existByEmail(email);
    }


    //Done
    @PutMapping("/confirmProjectStarted/{id}")
    public ResponseEntity<ResponseOrderDto> confirmProjectStarted(@PathVariable("id") int id) {
        return new ResponseEntity<>(customerService.confirmProjectStarted(id), HttpStatus.OK);
    }

    @PutMapping("/confirmedProjectFinished/{id}")
    public ResponseEntity<ResponseOrderDto> confirmedProjectFinished(@PathVariable("id") int id) {
        return new ResponseEntity<>(customerService.confirmedProjectFinished(id), HttpStatus.OK);
    }

        @PostMapping("/payByCredit/{orderId}/{finalPrice}")
        public ResponseEntity<ResponseOrderDto> payByCredit(
                @PathVariable("orderId") int orderId,
                @PathVariable("finalPrice") double finalPrice
        ) {
            return new ResponseEntity<>(customerService.payByCredit(orderId,finalPrice), HttpStatus.OK);

        }

        @PostMapping("/payOnline")
        public ResponseEntity<ResponseOrderDto> payOnline(
                @RequestParam("orderId") int orderId,
                @RequestParam("cardNumber") String cardNumber,
                @RequestParam("cvv2") String cvv2,
                @RequestParam("month") int month,
                @RequestParam("year") int year,
                @RequestParam("password") String password
        ) {
            return new ResponseEntity<>
                    (customerService.
                            payOnline(orderId,cardNumber,cvv2,month,year,password),
                            HttpStatus.OK);

        }
    }


