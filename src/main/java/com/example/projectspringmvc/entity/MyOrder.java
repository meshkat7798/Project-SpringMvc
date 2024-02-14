package com.example.projectspringmvc.entity;

import com.example.projectspringmvc.entity.enumeration.OrderStatus;
import com.example.projectspringmvc.entity.user.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@ToString
@AllArgsConstructor
@Entity
@SuppressWarnings("unused")
public class MyOrder{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JsonBackReference
    private Customer customer;


    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Specialist specialist;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private SubService subService;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Offer offer;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private double offeredPrice;

    private double finalPrice;

    @Column(nullable = false)
    private LocalDate dateOfNeed;

    private  String details;

    @CreationTimestamp
    private Timestamp creationDate;

    private LocalDate startedDate;

    private LocalTime statingHour;

    private LocalTime finishedHour;


    public MyOrder(Customer customer, SubService subService,
                   String address, double offeredPrice,
                   LocalDate dateOfNeed, String details) {
        this.customer = customer;
        this.subService = subService;
        this.address = address;
        this.offeredPrice = offeredPrice;
        this.dateOfNeed = dateOfNeed;
        this.details = details;

    }

    public MyOrder(){
        this.setOrderStatus(OrderStatus.AWAITING_SPECIALIST_OFFER);
    }

}
