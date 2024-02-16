package com.example.projectspringmvc.dto.response;

import com.example.projectspringmvc.entity.enumeration.OrderStatus;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ResponseOrderDto {

    private Integer id;

    private OrderStatus orderStatus;

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
