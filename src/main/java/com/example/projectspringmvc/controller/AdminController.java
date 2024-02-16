package com.example.projectspringmvc.controller;

import com.example.projectspringmvc.dto.AdminDto;
import com.example.projectspringmvc.dto.SpecialistDto;
import com.example.projectspringmvc.dto.response.ResponseCustomerDto;
import com.example.projectspringmvc.dto.response.ResponseSpecialistDto;
import com.example.projectspringmvc.service.impl.AdminServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminServiceImpl adminService;

    //Done
    @PostMapping("/save-user")
    public ResponseEntity<AdminDto> save(@RequestBody AdminDto user) {
        return new ResponseEntity<>(adminService.save(user), HttpStatus.OK);
    }


    //Done
    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<AdminDto> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(adminService.findById(id), HttpStatus.OK);
    }


    //Done
    @GetMapping("/find-by-username/{username}")
    public ResponseEntity<AdminDto> findByUsername(@PathVariable("username") String username) {
        return new ResponseEntity<>(adminService.findByUserName(username), HttpStatus.OK);
    }


    //Done
    @GetMapping("/find-all")
    public ResponseEntity<List<AdminDto>> findAll() {
        return new ResponseEntity<>(adminService.findAll(), HttpStatus.OK);
    }


    //Done
    @PutMapping("/update-user/{id}")
    public ResponseEntity<AdminDto> update(@PathVariable("id") int id,
                                           @RequestBody AdminDto adminDto) {
        adminDto.setId(id);
        return new ResponseEntity<>(adminService.update(adminDto), HttpStatus.OK);

    }

    //Done
    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<AdminDto> deleteById(@PathVariable("id") int id) {
        return new ResponseEntity<>(adminService.deleteById(id), HttpStatus.OK);
    }


    //Done
    @GetMapping("/existsById/{id}")
    public boolean existsById(@PathVariable Integer id) {

        return adminService.existsById(id);
    }

    //Done
    @GetMapping("/existByUserName/{username}")
    public boolean existByUserName(@PathVariable("username") String username) {
        return adminService.existByUserName(username);
    }

    //Done
    @PutMapping("/confirm-specialist/{id}")
    public ResponseEntity<SpecialistDto> confirmSpecialist(@PathVariable("id") int id,
                                                           @RequestBody SpecialistDto user) {
        user.setId(id);
        return new ResponseEntity<>(adminService.confirmSpecialist(id), HttpStatus.OK);
    }

    //Done
    @PutMapping("/disable-specialist/{id}")
    public ResponseEntity<SpecialistDto> disableSpecialist(@PathVariable("id") int id,
                                                           @RequestBody SpecialistDto user) {
        user.setId(id);
        return new ResponseEntity<>(adminService.disableSpecialist(id), HttpStatus.OK);
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

    //Done
    @GetMapping("/searchSpecialists")
    public List<ResponseSpecialistDto> searchSpecialists(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String specialization,
            @RequestParam(required = false) Boolean highScore){
        return adminService.searchSpecialists(firstName, lastName, email, specialization, highScore);
    }

    //Done
    @GetMapping("/searchCustomers")
    public List<ResponseCustomerDto> searchCustomers(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email) {
        return adminService.searchCustomers(firstName, lastName, email);
    }
}

