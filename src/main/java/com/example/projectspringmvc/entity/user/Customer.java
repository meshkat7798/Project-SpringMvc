package com.example.projectspringmvc.entity.user;

import com.example.projectspringmvc.entity.MyOrder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuppressWarnings("unused")
public class Customer{

    @Pattern(regexp = "^[a-zA-Z ]{2,30}$",message = "Invalid Name")
    private String firstname;

    @Pattern(regexp = "^[a-zA-Z ]{2,30}$",message = "Invalid Name")
    private String lastname;

    @Column(unique = true,nullable = false)
    @Pattern(regexp = "^[\\w-.]+@([\\w-]+.)+[\\w-]{2,4}$",message = "Invalid Email")
    private String email;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true,nullable = false)
    private String username;

    @Column(nullable = false)
    @Pattern(regexp =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",message = "Invalid Password")
    private String password;

    @CreationTimestamp
    private Timestamp creationDate;

    private double credit;


    @ToString.Exclude
    @OneToMany(mappedBy = "customer", cascade = CascadeType.MERGE)
    @JsonManagedReference
    private List<MyOrder> myOrders;

    public Customer(String firstname, String lastname, String email, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
    }
}

