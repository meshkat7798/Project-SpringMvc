package com.example.projectspringmvc.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuppressWarnings("unused")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private MyOrder myOrder;

    private int specialistScore;

    private String comment;

    @CreationTimestamp
    private Timestamp creationDate;


    public Comment(MyOrder myOrder, int specialistScore, String comment) {
        this.myOrder = myOrder;
        this.specialistScore = specialistScore;
        this.comment = comment;
    }
}
