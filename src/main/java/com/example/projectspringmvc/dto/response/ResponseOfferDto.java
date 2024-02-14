package com.example.projectspringmvc.dto.response;

import lombok.Data;

import java.time.LocalDate;
@Data
public class ResponseOfferDto {
    private Integer id;

    private double offeredPrice;

    private LocalDate offeredStartingDate;

    private double durationHoursOfOrder;
}
