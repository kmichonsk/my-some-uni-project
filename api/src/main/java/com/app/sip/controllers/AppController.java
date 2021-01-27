package com.app.sip.controllers;

import com.app.sip.dto.CreateStationDto;
import com.app.sip.dto.GetBrandDto;
import com.app.sip.dto.GetStationDto;
import com.app.sip.dto.UpdateStationDto;
import com.app.sip.services.AppService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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


    // support both by body and request params
    @PostMapping("/stations")
    public Long addStation(
            @RequestParam Optional<String> name,
            @RequestParam Optional<String> address,
            @RequestParam Optional<String> city,
            @RequestParam Optional<String> postalCode,
            @RequestParam Optional<Long> priceFuel95,
            @RequestParam Optional<Long> priceFuel98,
            @RequestParam Optional<Long> priceFuelDiesel,
            @RequestParam Optional<Long> priceFuelLpg,
            @RequestParam Optional<Long> brandId,
            @RequestParam Optional<String> openingHours,
            @RequestBody Optional<CreateStationDto> createStationDto) {
        if (name.isPresent() && address.isPresent() && city.isPresent() && postalCode.isPresent() &&
                priceFuel95.isPresent() && priceFuel98.isPresent() && priceFuelDiesel.isPresent() &&
                priceFuelLpg.isPresent() && brandId.isPresent() && openingHours.isPresent()) {
            return appService.addStation(
                    CreateStationDto.builder()
                            .name(name.get()).address(address.get()).city(city.get()).postalCode(postalCode.get())
                            .priceFuel95(priceFuel95.get()).priceFuel98(priceFuel98.get()).priceFuelDiesel(priceFuelDiesel.get())
                            .priceFuelLpg(priceFuelLpg.get()).brandId(brandId.get()).openingHours(openingHours.get())
                            .build());
        }
        else if (name.isPresent() || address.isPresent() || city.isPresent() || postalCode.isPresent() ||
                priceFuel95.isPresent() || priceFuel98.isPresent() || priceFuelDiesel.isPresent() ||
                priceFuelLpg.isPresent() || brandId.isPresent() || openingHours.isPresent()) {
            throw new RuntimeException("AppController: addStation: not all required request params specified");
        }
        else if (createStationDto.isEmpty()) {
            throw new RuntimeException("AppController: addStation: missing data in either request body or request params");
        }
        return appService.addStation(createStationDto.get());
    }

    @PatchMapping("/stations")
    public Long updateStation(
            @RequestParam Optional<Long> id,
            @RequestParam Optional<String> name,
            @RequestParam Optional<String> address,
            @RequestParam Optional<String> city,
            @RequestParam Optional<String> postalCode,
            @RequestParam Optional<Long> priceFuel95,
            @RequestParam Optional<Long> priceFuel98,
            @RequestParam Optional<Long> priceFuelDiesel,
            @RequestParam Optional<Long> priceFuelLpg,
            @RequestParam Optional<Long> brandId,
            @RequestParam Optional<String> openingHours,
            @RequestBody Optional<UpdateStationDto> updateStationDto) {
        if (name.isPresent() || address.isPresent() || city.isPresent() || postalCode.isPresent() ||
                priceFuel95.isPresent() || priceFuel98.isPresent() || priceFuelDiesel.isPresent() ||
                priceFuelLpg.isPresent() || brandId.isPresent() || openingHours.isPresent()) {
            return appService.updateStation(
                    UpdateStationDto.builder()
                            .id(id.orElse(null))
                            .name(name.orElse(null))
                            .address(address.orElse(null))
                            .city(city.orElse(null))
                            .postalCode(postalCode.orElse(null))
                            .priceFuel95(priceFuel95.orElse(null))
                            .priceFuel98(priceFuel98.orElse(null))
                            .priceFuelDiesel(priceFuelDiesel.orElse(null))
                            .priceFuelLpg(priceFuelLpg.orElse(null))
                            .brandId(brandId.orElse(null))
                            .openingHours(openingHours.orElse(null))
                            .build());
        }
        else if (updateStationDto.isEmpty()) {
            throw new RuntimeException("AppController: addStation: missing data in either request body or request params");
        }
        return appService.updateStation(updateStationDto.get());
    }
}
