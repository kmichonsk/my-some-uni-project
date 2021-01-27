package com.app.sip.dto;

import com.app.sip.model.Brand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetStationDto {
    private Long id;
    private String name;
    private String address;
    private String city;
    private String postalCode;
    private Double priceFuel95;
    private Double priceFuel98;
    private Double priceFuelDiesel;
    private Double priceFuelLpg;
    private GetBrandDto brand;
    private String openingHours;
    private Double latitude;
    private Double longitude;
}
