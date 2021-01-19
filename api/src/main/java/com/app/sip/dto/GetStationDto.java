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
    private Boolean hasFuel95;
    private Boolean hasFuel98;
    private Boolean hasFuelDiesel;
    private Boolean hasFuelLpg;
    private GetBrandDto brand;
    private String openingHours;
}
