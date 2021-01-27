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
    private Double priceFuel95;
    private Double priceFuel98;
    private Double priceFuelDiesel;
    private Double priceFuelLpg;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Brand brand;
    private String openingHours;

    private Double latitude;
    private Double longitude;
}
