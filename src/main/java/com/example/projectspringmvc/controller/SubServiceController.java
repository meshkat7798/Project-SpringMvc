package com.example.projectspringmvc.controller;


import com.example.projectspringmvc.dto.SubServiceDto;
import com.example.projectspringmvc.dto.response.ResponseSubServiceDto;
import com.example.projectspringmvc.service.impl.SubServiceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subService")
public class SubServiceController {

    private final SubServiceServiceImpl subServiceService;

    @PostMapping("/save-subService")
    public ResponseEntity<SubServiceDto> save(@RequestBody SubServiceDto subServiceDto) {
        return new ResponseEntity<SubServiceDto>(subServiceService.save(subServiceDto), HttpStatus.OK);
    }


    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<ResponseSubServiceDto> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<ResponseSubServiceDto>(subServiceService.findById(id), HttpStatus.OK);
    }


    @GetMapping("/find-all")
    public ResponseEntity<List<ResponseSubServiceDto>> findAll() {
        return new ResponseEntity<List<ResponseSubServiceDto>>(subServiceService.findAll(), HttpStatus.OK);
    }


    @PutMapping("/update-subService/{id}")
    public ResponseEntity<ResponseSubServiceDto> update(@PathVariable("id") int id, @RequestBody ResponseSubServiceDto subServiceDto) {
        subServiceDto.setId(id);
        return new ResponseEntity<ResponseSubServiceDto>(subServiceService.updateSubService(subServiceDto), HttpStatus.OK);

    }

    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<SubServiceDto> deleteById(@PathVariable("id") int id) {
        return new ResponseEntity<SubServiceDto>(subServiceService.deleteById(id), HttpStatus.OK);
    }


    @GetMapping("/existsById/{id}")
    public boolean existsById(@PathVariable Integer id) {
        return subServiceService.existsById(id);
    }

    @GetMapping("/existByName/{name}")
    public boolean existByName(@PathVariable("name") String name) {
        return subServiceService.existByName(name);
    }

    @GetMapping("/subServicesOfOneService/{serviceId}")
    public ResponseEntity<List<ResponseSubServiceDto>> subServicesOfOneService(@PathVariable("serviceId") int serviceId) {
        return new ResponseEntity<List<ResponseSubServiceDto>>(subServiceService.subServicesOfOneService(serviceId), HttpStatus.OK);
    }
}
