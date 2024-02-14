package com.example.projectspringmvc.dto.response;

import lombok.Data;

@Data
public class ResponseCustomerDto {

    private Integer id;

    private String firstname;

    private String lastname;

    private String email;

    private String username;

    private String password;
}
