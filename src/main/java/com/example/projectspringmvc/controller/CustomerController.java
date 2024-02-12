package com.example.projectspringmvc.controller;

import com.example.projectspringmvc.dto.AdminDto;
import com.example.projectspringmvc.dto.CustomerDto;
import com.example.projectspringmvc.dto.MyOrderDto;
import com.example.projectspringmvc.dto.SpecialistDto;
import com.example.projectspringmvc.entity.user.Admin;
import com.example.projectspringmvc.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/save-user")
    public ResponseEntity<CustomerDto> save(@RequestBody CustomerDto user) {
        return new ResponseEntity<CustomerDto>(customerService.save(user), HttpStatus.OK);
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<CustomerDto> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<CustomerDto>(customerService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/find-by-username/{username}")
    public ResponseEntity<CustomerDto> findByUsername(@PathVariable("username") String username) {
        return new ResponseEntity<CustomerDto>(customerService.findByUserName(username), HttpStatus.OK);
    }


    @GetMapping("/find-all")
    public ResponseEntity<List<CustomerDto>> findAll() {
        return new ResponseEntity<List<CustomerDto>>(customerService.findAll(), HttpStatus.OK);
    }


    @PutMapping("/update-user/{id}")
    public ResponseEntity<CustomerDto> update(@PathVariable("id") int id, @RequestBody CustomerDto customerDto) {
        customerDto.setId(id);
        return new ResponseEntity<CustomerDto>(customerService.update(customerDto), HttpStatus.OK);

    }

    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<CustomerDto> deleteById(@PathVariable("id") int id) {
        return new ResponseEntity<CustomerDto>(customerService.deleteById(id), HttpStatus.OK);
    }


    @GetMapping("/existsById/{id}")
    public boolean existsById(@PathVariable("id") Integer id) {
        return customerService.existsById(id);
    }

    @GetMapping("/existByUserName/{username}")
    public boolean existByUserName(@PathVariable("username") String username) {
        return customerService.existByUserName(username);
    }

    @GetMapping("/existByEmail/{email}")
    public boolean existByEmail(@PathVariable("email") String email) {
        return customerService.existByEmail(email);
    }

    @PutMapping("/confirmProjectStarted/{id}")
    public ResponseEntity<MyOrderDto> confirmProjectStarted(@PathVariable("id") int id, @RequestBody MyOrderDto orderDto) {
        orderDto.setId(id);
        return new ResponseEntity<MyOrderDto>(customerService.confirmProjectStarted(id), HttpStatus.OK);
    }

    @PutMapping("/confirmedProjectFinished/{id}")
    public ResponseEntity<MyOrderDto> confirmedProjectFinished(@PathVariable("id") int id, @RequestBody MyOrderDto orderDto) {
        orderDto.setId(id);
        return new ResponseEntity<MyOrderDto>(customerService.confirmedProjectFinished(id), HttpStatus.OK);
    }

}
