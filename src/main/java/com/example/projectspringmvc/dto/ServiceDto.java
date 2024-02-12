package com.example.projectspringmvc.dto;

import com.example.projectspringmvc.entity.Service;
import com.example.projectspringmvc.entity.user.Specialist;
import lombok.Data;

import java.util.List;

@Data
public class ServiceDto {

     private Integer id;

    private List<Specialist> specialists;

    private Service service;

    private String name;

    private double basePrice;

    private String details;
}
