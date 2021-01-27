package com.app.sip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStationDto {
    private Long id;
    private String name;
    private String address;
    private String city;
    private String postalCode;
    private Long priceFuel95;
    private Long priceFuel98;
    private Long priceFuelDiesel;
    private Long priceFuelLpg;
    private Long brandId;
    private String openingHours;
}
