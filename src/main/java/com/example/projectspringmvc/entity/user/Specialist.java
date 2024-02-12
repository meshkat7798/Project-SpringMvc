package com.example.projectspringmvc.entity.user;
import com.example.projectspringmvc.entity.MyOrder;
import com.example.projectspringmvc.entity.*;
import com.example.projectspringmvc.entity.enumeration.SpecialistStatus;
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
public class Specialist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Pattern(regexp = "^[a-zA-Z ]{2,30}$",message = "Invalid Name")
    private String firstname;

    @Pattern(regexp = "^[a-zA-Z ]{2,30}$",message = "Invalid Name")
    private String lastname;

    @Column(unique = true,nullable = false)
    @Pattern(regexp = "^[\\w-.]+@([\\w-]+.)+[\\w-]{2,4}$",message = "Invalid Email")
    private String email;


    @Column(unique = true,nullable = false)
    private String username;

    @Column(nullable = false)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",message = "Invalid Password")
    private String password;

    @CreationTimestamp
    private Timestamp creationDate;

    private double credit;

    @Column
    @Enumerated(EnumType.STRING)
    private SpecialistStatus specialistStatus;

    @Lob
    private byte[] profilePicture;

    @ElementCollection
    private List<Integer> specialistScores;

    private double averageScore;

    private String specialities;

    @ToString.Exclude
    @ManyToMany(mappedBy = "specialists")
    private List<SubService> subServices;

    @ToString.Exclude
    @ManyToMany(mappedBy = "specialists")
    private List<Service> services;

    @ToString.Exclude
    @OneToMany(mappedBy = "specialist", cascade = CascadeType.MERGE)
    private List<MyOrder> myOrders;

    @ToString.Exclude
    @OneToMany(mappedBy = "specialist", cascade = CascadeType.MERGE)
    private List<Offer> offers;

    public Specialist(String firstname, String lastname, String email, String username, String password, String specialities) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.specialities = specialities;
        this.specialistStatus = SpecialistStatus.NEW;
        this.specialistScores = new ArrayList<>();
        this.setCredit(0);
        this.setServices(new ArrayList<>());
        this.setSubServices(new ArrayList<>());
        this.setOffers(new ArrayList<>());
        this.setMyOrders(new ArrayList<>());
    }

    public void addSubService(SubService subService) {
    }
}
