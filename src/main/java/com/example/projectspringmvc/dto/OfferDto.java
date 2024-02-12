package com.example.projectspringmvc.dto;

import com.example.projectspringmvc.entity.MyOrder;
import com.example.projectspringmvc.entity.user.Specialist;
import lombok.Data;


import java.sql.Timestamp;
import java.time.LocalDate;

@Data
public class OfferDto {

    private Integer id;

    private Specialist specialist;

    private MyOrder order;

    private Timestamp creationDate;

    private double offeredPrice;

    private LocalDate offeredStartingDate;

    private double durationHoursOfOrder;
}
