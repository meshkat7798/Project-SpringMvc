package com.example.projectspringmvc.dto.response;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ResponseCommentDto {

    private Integer id;

    private int specialistScore;

    private String comment;

    private Timestamp creationDate;
}
