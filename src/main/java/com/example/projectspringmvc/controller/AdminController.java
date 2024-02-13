package com.example.projectspringmvc.controller;

import com.example.projectspringmvc.dto.AdminDto;
import com.example.projectspringmvc.dto.SpecialistDto;
import com.example.projectspringmvc.entity.user.Customer;
import com.example.projectspringmvc.entity.user.Specialist;
import com.example.projectspringmvc.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/save-user")
    public ResponseEntity<AdminDto> save(@RequestBody AdminDto user) {
        return new ResponseEntity<AdminDto>(adminService.save(user), HttpStatus.OK);
    }


    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<AdminDto> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<AdminDto>(adminService.findById(id), HttpStatus.OK);
    }


    @GetMapping("/find-by-username/{username}")
    public ResponseEntity<AdminDto> findByUsername(@PathVariable("username") String username) {
        return new ResponseEntity<AdminDto>(adminService.findByUserName(username), HttpStatus.OK);
    }


    @GetMapping("/find-all")
    public ResponseEntity<List<AdminDto>> findAll() {
        return new ResponseEntity<List<AdminDto>>(adminService.findAll(), HttpStatus.OK);
    }


    @PutMapping("/update-user/{id}")
    public ResponseEntity<AdminDto> update(@PathVariable("id") int id,
                                           @RequestBody AdminDto adminDto) {
        adminDto.setId(id);
        return new ResponseEntity<AdminDto>(adminService.update(adminDto), HttpStatus.OK);

    }

    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<AdminDto> deleteById(@PathVariable("id") int id) {
        return new ResponseEntity<AdminDto>(adminService.deleteById(id), HttpStatus.OK);
    }


    @GetMapping("/existsById/{id}")
    public boolean existsById(@PathVariable Integer id) {

        return adminService.existsById(id);
    }

    @GetMapping("/existByUserName/{username}")
    public boolean existByUserName(@PathVariable("username") String username) {
        return adminService.existByUserName(username);
    }

    @PutMapping("/confirm-specialist/{id}")
    public ResponseEntity<SpecialistDto> confirmSpecialist(@PathVariable("id") int id,
                                                           @RequestBody SpecialistDto user) {
        user.setId(id);
        return new ResponseEntity<SpecialistDto>(adminService.confirmSpecialist(id), HttpStatus.OK);
    }

    @PutMapping("/disable-specialist/{id}")
    public ResponseEntity<SpecialistDto> disableSpecialist(@PathVariable("id") int id,
                                                           @RequestBody SpecialistDto user) {
        user.setId(id);
        return new ResponseEntity<SpecialistDto>(adminService.disableSpecialist(id), HttpStatus.OK);
    }

    @PutMapping("/add-specialist-to-subService/{sub-id}/{spec-id}")
    public void addSpecialistToSubService(@PathVariable("sub-id") int subServiceId, @PathVariable("spec-id") int specialistId) {
        adminService.addSpecialistToSubService(subServiceId, specialistId);
    }

    @PutMapping("/remove-specialist-from-subService/{sub-id}/{spec-id}")
    public void removeSpecialistFromSubService(@PathVariable("sub-id") int subServiceId,
                                               @PathVariable("spec-id") int specialistId) {
        adminService.removeSpecialistFromSubService(subServiceId, specialistId);
    }

    @GetMapping("/searchSpecialists")
    public List<Specialist> searchSpecialists(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String specialization) {
        return adminService.searchSpecialists(firstName, lastName, email, specialization);
    }

    @GetMapping("/searchCustomers")
    public List<Customer> searchCustomers(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email) {
        return adminService.searchCustomers(firstName, lastName, email);
    }
}

