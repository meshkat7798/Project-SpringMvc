package com.example.projectspringmvc.dto;

import com.example.projectspringmvc.entity.MyOrder;
import com.example.projectspringmvc.entity.Offer;
import com.example.projectspringmvc.entity.Service;
import com.example.projectspringmvc.entity.SubService;
import com.example.projectspringmvc.entity.enumeration.SpecialistStatus;
import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

@Data
public class SpecialistDto {

    private Integer id;

    private String firstname;

    private String lastname;

    private String email;

    private String username;

    private String password;

    private Timestamp creationDate;

    private double credit;

    private SpecialistStatus specialistStatus;

    private byte[] profilePicture;

    private List<Integer> specialistScores;

    private double averageScore;

    private String specialities;

     private List<SubService> subServices;

    private List<Service> services;

    private List<MyOrder> myOrders;

    private List<Offer> offers;
}
