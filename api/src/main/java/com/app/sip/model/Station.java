package com.app.sip.model;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stations")
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String city;
    private String postalCode;
    private Boolean hasFuel95;
    private Boolean hasFuel98;
    private Boolean hasFuelDiesel;
    private Boolean hasFuelLpg;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Brand brand;
    private String openingHours;
}
