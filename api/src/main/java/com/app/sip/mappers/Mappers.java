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
                .hasFuel95(station.getHasFuel95())
                .hasFuel98(station.getHasFuel98())
                .hasFuelDiesel(station.getHasFuelDiesel())
                .hasFuelLpg(station.getHasFuelLpg())
                .postalCode(station.getPostalCode())
                .openingHours(station.getOpeningHours())
                .build();
    }

    static Station fromCreateStationDtoToStation(CreateStationDto createStationDto) {
        return Station.builder()
                .name(createStationDto.getName())
                .city(createStationDto.getCity())
                .address(createStationDto.getAddress())
                .hasFuel95(createStationDto.getHasFuel95())
                .hasFuel98(createStationDto.getHasFuel98())
                .hasFuelDiesel(createStationDto.getHasFuelDiesel())
                .hasFuelLpg(createStationDto.getHasFuelLpg())
                .postalCode(createStationDto.getPostalCode())
                .openingHours(createStationDto.getOpeningHours())
                .build();
    }
}
