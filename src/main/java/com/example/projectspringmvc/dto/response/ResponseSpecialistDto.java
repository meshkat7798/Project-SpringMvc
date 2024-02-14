package com.example.projectspringmvc.dto.response;

import com.example.projectspringmvc.entity.MyOrder;
import com.example.projectspringmvc.entity.Offer;
import com.example.projectspringmvc.entity.Service;
import com.example.projectspringmvc.entity.SubService;
import com.example.projectspringmvc.entity.enumeration.SpecialistStatus;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class ResponseSpecialistDto {
    private Integer id;

    private String firstname;

    private String lastname;

    private String email;

    private String username;

    private String password;

    private double credit;

    private SpecialistStatus specialistStatus;

    private double averageScore;

    private String specialities;

}
