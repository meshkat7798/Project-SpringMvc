package com.example.projectspringmvc.entity;

import com.example.projectspringmvc.entity.user.Specialist;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuppressWarnings("unused")
public class Offer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Specialist specialist;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private MyOrder order;

    @CreationTimestamp
    private Timestamp creationDate;

    private double offeredPrice;

    private LocalDate offeredStartingDate;

    private double durationHoursOfOrder;

    public Offer(MyOrder order, Specialist specialist,
                 double offeredPrice,
                 LocalDate offeredStartingDate,
                 double durationHoursOfOrder) {
        this.order =order;
        this.specialist = specialist;
        this.offeredPrice = offeredPrice;
        this.offeredStartingDate = offeredStartingDate;
        this.durationHoursOfOrder = durationHoursOfOrder;
    }
}
