package com.example.projectspringmvc.controller;

import com.example.projectspringmvc.dto.SpecialistDto;
import com.example.projectspringmvc.dto.response.ResponseSpecialistDto;
import com.example.projectspringmvc.service.impl.SpecialistServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/specialist")
public class SpecialistController {

    private final SpecialistServiceImpl specialistService;

    //Done
    @PostMapping("/save-user")
    public ResponseEntity<SpecialistDto> save(@RequestBody SpecialistDto user) {
        return new ResponseEntity<>(specialistService.save(user), HttpStatus.OK);
    }


    //Done
    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<ResponseSpecialistDto> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(specialistService.findById(id), HttpStatus.OK);
    }


    //Done
    @GetMapping("/find-by-username/{username}")
    public ResponseEntity<ResponseSpecialistDto> findByUsername(@PathVariable("username") String username) {
        return new ResponseEntity<>(specialistService.findByUserName(username), HttpStatus.OK);
    }


    //Done
    @GetMapping("/find-all")
    public ResponseEntity<List<ResponseSpecialistDto>> findAll() {
        return new ResponseEntity<>(specialistService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/showSpecialistScores/{id}")
    public ResponseEntity<List<Integer>> showSpecialistScores(@PathVariable("id") int specialistId) {
        return new ResponseEntity<>(specialistService.showSpecialistScores(specialistId), HttpStatus.OK);
    }


    //Done
    @PutMapping("/update-user/{id}")
    public ResponseEntity<ResponseSpecialistDto> update(@PathVariable("id") int id, @RequestBody ResponseSpecialistDto specialistDto) {
        specialistDto.setId(id);
        return new ResponseEntity<>(specialistService.update(specialistDto), HttpStatus.OK);

    }

    //Done
    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<SpecialistDto> deleteById(@PathVariable("id") int id) {
        return new ResponseEntity<>(specialistService.deleteById(id), HttpStatus.OK);
    }


    //Done
    @GetMapping("/existsById/{id}")
    public boolean existsById(@PathVariable Integer id) {
        return specialistService.existsById(id);
    }

    //Done
    @GetMapping("/existByUserName/{username}")
    public boolean existByUserName(@PathVariable("username") String username) {
        return specialistService.existByUserName(username);
    }


    //Done
    @GetMapping("/existByEmail/{email}")
    public boolean existByEmail(@PathVariable("email") String email) {
        return specialistService.existByEmail(email);
    }


    //Done
    @GetMapping("/hasAccessToSystem/{id}")
    public boolean hasAccessToSystem(@PathVariable Integer id) {
        return specialistService.hasAccessToSystem(id);
    }


    @PostMapping("/{specialistId}/profilePicture")
    public ResponseEntity<String> addProfilePicture(
            @PathVariable("specialistId") int specialistId,
            @RequestParam("imagePath") String imagePath
    ) {
        specialistService.addProfilePicture(specialistId, imagePath);
        return ResponseEntity.ok("Profile picture added successfully");
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveImageFromBytes(
            @RequestParam("imageData") byte[] imageData,
            @RequestParam("outputPath") String outputPath
    ) {
        try {
            saveImage(imageData, outputPath);
            return ResponseEntity.ok("Image saved successfully to: " + outputPath);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving image");
        }
    }

    private void saveImage(byte[] imageData, String outputPath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(outputPath)) {
            fos.write(imageData);
        }
        System.out.println("Image saved successfully to: " + outputPath);
    }



}

