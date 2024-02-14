package com.example.projectspringmvc.dto.response;

import com.example.projectspringmvc.entity.Offer;
import com.example.projectspringmvc.entity.SubService;
import com.example.projectspringmvc.entity.enumeration.OrderStatus;
import com.example.projectspringmvc.entity.user.Customer;
import com.example.projectspringmvc.entity.user.Specialist;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
public class ResponseOrderDto {

    private Integer id;

    private OrderStatus orderStatus;

    private String address;

    private double offeredPrice;

    private double finalPrice;

    private LocalDate dateOfNeed;

    private String details;
}
