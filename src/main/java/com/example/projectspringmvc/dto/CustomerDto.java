package com.example.projectspringmvc.dto;

import com.example.projectspringmvc.entity.MyOrder;
import lombok.Data;


import java.sql.Timestamp;
import java.util.List;
@Data
public class CustomerDto {

    private String firstname;

    private String lastname;

    private String email;

    private Integer id;

    private String username;

    private String password;

    private Timestamp creationDate;

    private double credit;

    private List<MyOrder> myOrders;
}
