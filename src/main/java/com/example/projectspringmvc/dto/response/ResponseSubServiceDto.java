package com.example.projectspringmvc.dto.response;

import com.example.projectspringmvc.entity.Service;
import lombok.Data;

@Data
public class ResponseSubServiceDto {

    private Integer id;

    private String name;

    private double basePrice;

    private String details;
}
