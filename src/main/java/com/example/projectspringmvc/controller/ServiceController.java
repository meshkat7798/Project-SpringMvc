package com.example.projectspringmvc.controller;

import com.example.projectspringmvc.dto.AdminDto;
import com.example.projectspringmvc.dto.ServiceDto;
import com.example.projectspringmvc.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/service")
public class ServiceController {

    private final ServiceService serviceService;

    @PostMapping("/save-service")
    public ResponseEntity<ServiceDto> save(@RequestBody ServiceDto serviceDto) {
        return new ResponseEntity<ServiceDto>(serviceService.save(serviceDto), HttpStatus.OK);
    }


    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<ServiceDto> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<ServiceDto>(serviceService.findById(id), HttpStatus.OK);
    }


    @GetMapping("/find-all")
    public ResponseEntity<List<ServiceDto>> findAll() {
        return new ResponseEntity<List<ServiceDto>>(serviceService.findAll(), HttpStatus.OK);
    }


    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<ServiceDto> deleteById(@PathVariable("id") int id) {
        return new ResponseEntity<ServiceDto>(serviceService.deleteById(id), HttpStatus.OK);
    }


    @GetMapping("/existsById/{id}")
    public boolean existsById(@PathVariable Integer id) {
        return serviceService.existsById(id);
    }


    @GetMapping("/existByUserName/{name}")
    public boolean existByUserName(@PathVariable("name") String username) {
        return serviceService.existByName(username);
    }

}
