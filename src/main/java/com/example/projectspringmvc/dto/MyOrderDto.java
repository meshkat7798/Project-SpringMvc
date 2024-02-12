package com.example.projectspringmvc.dto;

import com.example.projectspringmvc.entity.Offer;
import com.example.projectspringmvc.entity.SubService;
import com.example.projectspringmvc.entity.enumeration.OrderStatus;
import com.example.projectspringmvc.entity.user.Customer;
import com.example.projectspringmvc.entity.user.Specialist;
import lombok.Data;
import java.sql.Timestamp;
import java.time.LocalDate;
@Data
public class MyOrderDto {

    private Integer id;

    private Customer customer;

    private Specialist specialist;

    private OrderStatus orderStatus;

    private SubService subService;

    private Offer offer;

    private String address;

    private double offeredPrice;

    private double finalPrice;

    private LocalDate dateOfNeed;

    private String details;

    private Timestamp creationDate;
}
