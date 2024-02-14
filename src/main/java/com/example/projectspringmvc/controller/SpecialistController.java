package com.example.projectspringmvc.controller;

import com.example.projectspringmvc.dto.SpecialistDto;
import com.example.projectspringmvc.dto.response.ResponseSpecialistDto;
import com.example.projectspringmvc.service.impl.SpecialistServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/specialist")
public class SpecialistController {

    private final SpecialistServiceImpl specialistService;

    @PostMapping("/save-user")
    public ResponseEntity<SpecialistDto> save(@RequestBody SpecialistDto user) {
        return new ResponseEntity<SpecialistDto>(specialistService.save(user), HttpStatus.OK);
    }


    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<ResponseSpecialistDto> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<ResponseSpecialistDto>(specialistService.findById(id), HttpStatus.OK);
    }


    @GetMapping("/find-by-username/{username}")
    public ResponseEntity<ResponseSpecialistDto> findByUsername(@PathVariable("username") String username) {
        return new ResponseEntity<ResponseSpecialistDto>(specialistService.findByUserName(username), HttpStatus.OK);
    }


    @GetMapping("/find-all")
    public ResponseEntity<List<ResponseSpecialistDto>> findAll() {
        return new ResponseEntity<List<ResponseSpecialistDto>>(specialistService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/showSpecialistScores/{id}")
    public ResponseEntity<List<Integer>> showSpecialistScores(@PathVariable("id") int specialistId) {
        return new ResponseEntity<List<Integer>>(specialistService.showُSpecialistScores(specialistId), HttpStatus.OK);
    }


    @PutMapping("/update-user/{id}")
    public ResponseEntity<ResponseSpecialistDto> update(@PathVariable("id") int id, @RequestBody ResponseSpecialistDto specialistDto) {
        specialistDto.setId(id);
        return new ResponseEntity<ResponseSpecialistDto>(specialistService.update(specialistDto), HttpStatus.OK);

    }

    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<SpecialistDto> deleteById(@PathVariable("id") int id) {
        return new ResponseEntity<SpecialistDto>(specialistService.deleteById(id), HttpStatus.OK);
    }


    @GetMapping("/existsById/{id}")
    public boolean existsById(@PathVariable Integer id) {
        return specialistService.existsById(id);
    }

    @GetMapping("/existByUserName/{username}")
    public boolean existByUserName(@PathVariable("username") String username) {
        return specialistService.existByUserName(username);
    }

    @GetMapping("/existByEmail/{email}")
    public boolean existByEmail(@PathVariable("email") String email) {
        return specialistService.existByEmail(email);
    }

    @GetMapping("/hasAccessToSystem/{id}")
    public boolean hasAccessToSystem(@PathVariable Integer id) {
        return specialistService.hasAccessToSystem(id);
    }


}
