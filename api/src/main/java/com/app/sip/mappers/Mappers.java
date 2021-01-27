package com.app.sip.mappers;

import com.app.sip.dto.CreateStationDto;
import com.app.sip.dto.GetBrandDto;
import com.app.sip.dto.GetStationDto;
import com.app.sip.dto.UpdateStationDto;
import com.app.sip.model.Brand;
import com.app.sip.model.Station;

public interface Mappers {
    static GetBrandDto fromBrandToGetBrandDto(Brand brand) {
        return GetBrandDto.builder()
                .id(brand.getId())
                .name(brand.getName())
                .build();
    }

    static GetStationDto fromStationToGetStationDto(Station station) {
        return GetStationDto.builder()
                .id(station.getId())
                .name(station.getName())
                .city(station.getCity())
                .address(station.getAddress())
                .brand(fromBrandToGetBrandDto(station.getBrand()))
                .priceFuel95(station.getPriceFuel95())
                .priceFuel98(station.getPriceFuel98())
                .priceFuelDiesel(station.getPriceFuelDiesel())
                .priceFuelLpg(station.getPriceFuelLpg())
                .postalCode(station.getPostalCode())
                .openingHours(station.getOpeningHours())
                .build();
    }

    static Station fromCreateStationDtoToStation(CreateStationDto createStationDto) {
        return Station.builder()
                .name(createStationDto.getName())
                .city(createStationDto.getCity())
                .address(createStationDto.getAddress())
                .priceFuel95(createStationDto.getPriceFuel95())
                .priceFuel98(createStationDto.getPriceFuel98())
                .priceFuelDiesel(createStationDto.getPriceFuelDiesel())
                .priceFuelLpg(createStationDto.getPriceFuelLpg())
                .postalCode(createStationDto.getPostalCode())
                .openingHours(createStationDto.getOpeningHours())
                .build();
    }
}
