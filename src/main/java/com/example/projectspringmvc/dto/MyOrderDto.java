package com.example.projectspringmvc.dto;

import com.example.projectspringmvc.entity.Offer;
import com.example.projectspringmvc.entity.SubService;
import com.example.projectspringmvc.entity.enumeration.OrderStatus;
import com.example.projectspringmvc.entity.user.Customer;
import com.example.projectspringmvc.entity.user.Specialist;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class MyOrderDto {

    private Integer id;

    private Customer customer;


    private Specialist specialist;

    private OrderStatus orderStatus;

    private SubService subService;

    private List<Offer> offers;

    private int predictedDuration;

    private String address;

    private double offeredPrice;

    private double finalPrice;

    private LocalDate dateOfNeed;

    private String details;

    private Timestamp creationDate;

    private LocalDate startedDate;

    private LocalTime statingHour;

    private LocalTime finishedHour;

}
