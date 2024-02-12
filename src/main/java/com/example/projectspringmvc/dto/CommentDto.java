package com.example.projectspringmvc.dto;

import com.example.projectspringmvc.entity.MyOrder;
import lombok.Data;
import java.sql.Timestamp;


@Data
public class CommentDto {

    private Integer id;

    private MyOrder myOrder;

    private int specialistScore;

    private String comment;

    private Timestamp creationDate;
}
