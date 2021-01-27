package com.app.sip.controllers;

import com.app.sip.dto.CreateStationDto;
import com.app.sip.dto.GetBrandDto;
import com.app.sip.dto.GetStationDto;
import com.app.sip.dto.UpdateStationDto;
import com.app.sip.services.AppService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
@RequiredArgsConstructor
public class AppController {
    private final AppService appService;

    @GetMapping("/brands")
    public List<GetBrandDto> getAllBrands() { return appService.getAllBrands(); }

    @GetMapping("/stations")
    public List<GetStationDto> getAllStations() {
        return appService.getAllStations();
    }

    @PostMapping("/stations")
    public Long addStation(
            @RequestParam String name,
            @RequestParam String address,
            @RequestParam String city,
            @RequestParam String postalCode,
            @RequestParam String priceFuel95,
            @RequestParam String priceFuel98,
            @RequestParam String priceFuelDiesel,
            @RequestParam String priceFuelLpg,
            @RequestParam String brandId,
            @RequestParam String openingHours) {
        if (priceFuel95.isBlank()) priceFuel95 = "0.0";
        if (priceFuel98.isBlank()) priceFuel98 = "0.0";
        if (priceFuelDiesel.isBlank()) priceFuelDiesel = "0.0";
        if (priceFuelLpg.isBlank()) priceFuelLpg = "0.0";
        return appService.addStation(
                CreateStationDto.builder()
                        .name(name)
                        .address(address)
                        .city(city)
                        .postalCode(postalCode)
                        .priceFuel95(Double.valueOf(priceFuel95))
                        .priceFuel98(Double.valueOf(priceFuel98))
                        .priceFuelDiesel(Double.valueOf(priceFuelDiesel))
                        .priceFuelLpg(Double.valueOf(priceFuelLpg))
                        .brandId(Long.valueOf(brandId))
                        .openingHours(openingHours)
                        .build());
    }

    @PatchMapping("/stations")
    public Long updateStation(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String postalCode,
            @RequestParam(required = false) Double priceFuel95,
            @RequestParam(required = false) Double priceFuel98,
            @RequestParam(required = false) Double priceFuelDiesel,
            @RequestParam(required = false) Double priceFuelLpg,
            @RequestParam(required = false) Long brandId,
            @RequestParam(required = false) String openingHours) {
        return appService.updateStation(
                UpdateStationDto.builder()
                        .id(id)
                        .name(name)
                        .address(address)
                        .city(city)
                        .postalCode(postalCode)
                        .priceFuel95(priceFuel95)
                        .priceFuel98(priceFuel98)
                        .priceFuelDiesel(priceFuelDiesel)
                        .priceFuelLpg(priceFuelLpg)
                        .brandId(brandId)
                        .openingHours(openingHours)
                        .build());
    }
}
