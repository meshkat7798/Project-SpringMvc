package com.example.projectspringmvc.controller;

import com.example.projectspringmvc.dto.ServiceDto;
import com.example.projectspringmvc.dto.response.ResponseServiceDto;
import com.example.projectspringmvc.service.impl.ServiceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/service")
public class ServiceController {

    private final ServiceServiceImpl serviceService;


    //Done
    @PostMapping("/save-service")
    public ResponseEntity<ServiceDto> save(@RequestBody ServiceDto serviceDto) {
        return new ResponseEntity<>(serviceService.save(serviceDto), HttpStatus.OK);
    }


    //Done
    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<ResponseServiceDto> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(serviceService.findById(id), HttpStatus.OK);
    }


    //Done
    @GetMapping("/find-all")
    public ResponseEntity<List<ResponseServiceDto>> findAll() {
        return new ResponseEntity<>(serviceService.findAll(), HttpStatus.OK);
    }


    //Done
    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<ServiceDto> deleteById(@PathVariable("id") int id) {
        return new ResponseEntity<>(serviceService.deleteById(id), HttpStatus.OK);
    }


    //Done
    @GetMapping("/existsById/{id}")
    public boolean existsById(@PathVariable Integer id) {
        return serviceService.existsById(id);
    }


    //Done
    @GetMapping("/existByName/{name}")
    public boolean existByName(@PathVariable("name") String username) {
        return serviceService.existByName(username);
    }

}
