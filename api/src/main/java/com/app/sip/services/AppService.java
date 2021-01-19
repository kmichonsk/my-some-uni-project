package com.app.sip.services;

import com.app.sip.dto.CreateStationDto;
import com.app.sip.dto.GetBrandDto;
import com.app.sip.dto.GetStationDto;
import com.app.sip.dto.UpdateStationDto;
import com.app.sip.mappers.Mappers;
import com.app.sip.repositories.BrandsRepository;
import com.app.sip.repositories.StationsRepository;
import com.app.sip.validator.CreateStationValidator;
import com.app.sip.validator.UpdateStationValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AppService {
    private final BrandsRepository brandsRepository;
    private final StationsRepository stationsRepository;

    public Long addStation(CreateStationDto createStationDto) {
        Objects.requireNonNull(createStationDto, "AppService: addStation: createStationDto is null");
        var createStationValidator = new CreateStationValidator();
        var errors = createStationValidator.validate(createStationDto);
        if (createStationValidator.hasErrors()) {
            var message = errors
                    .entrySet()
                    .stream()
                    .map(e -> e.getKey() + ": " + e.getValue())
                    .collect(Collectors.joining(", "));
            throw new RuntimeException(message);
        }

        var brand = brandsRepository.findById(createStationDto.getBrandId()).orElseThrow(() -> {
            throw new RuntimeException("AppService: addStation: no brand with id: " + createStationDto.getBrandId());
        });
        var station = Mappers.fromCreateStationDtoToStation(createStationDto);
        station.setBrand(brand);
        return stationsRepository.save(station).getId();
    }

    public Long updateStation(UpdateStationDto updateStationDto) {
        Objects.requireNonNull(updateStationDto, "AppService: updateStation: createStationDto is null");
        var updateStationValidator = new UpdateStationValidator();
        var errors = updateStationValidator.validate(updateStationDto);
        if (updateStationValidator.hasErrors()) {
            var message = errors
                    .entrySet()
                    .stream()
                    .map(e -> e.getKey() + ": " + e.getValue())
                    .collect(Collectors.joining(", "));
            throw new RuntimeException(message);
        }

        var station = stationsRepository.findById(updateStationDto.getId()).orElseThrow(()-> {
                    throw new RuntimeException("AppService: updateStation: no station with id: " + updateStationDto.getId());
        });

        if (Objects.nonNull(updateStationDto.getBrandId())) {
            var brand = brandsRepository.findById(updateStationDto.getBrandId()).orElseThrow(() -> {
                throw new RuntimeException("AppService: updateStation: no brand with id: " + updateStationDto.getBrandId());
            });
            station.setBrand(brand);
        }

        if (Objects.nonNull(updateStationDto.getCity())) {
            station.setCity(updateStationDto.getCity());
        }
        if (Objects.nonNull(updateStationDto.getAddress())) {
            station.setAddress(updateStationDto.getAddress());
        }
        if (Objects.nonNull(updateStationDto.getName())) {
            station.setName(updateStationDto.getName());
        }
        if (Objects.nonNull(updateStationDto.getHasFuel95())) {
            station.setHasFuel95(updateStationDto.getHasFuel95());
        }
        if (Objects.nonNull(updateStationDto.getHasFuel98())) {
            station.setHasFuel98(updateStationDto.getHasFuel98());
        }
        if (Objects.nonNull(updateStationDto.getHasFuelDiesel())) {
            station.setHasFuelDiesel(updateStationDto.getHasFuelDiesel());
        }
        if (Objects.nonNull(updateStationDto.getHasFuelLpg())) {
            station.setHasFuelLpg(updateStationDto.getHasFuelLpg());
        }
        if (Objects.nonNull(updateStationDto.getPostalCode())) {
            station.setPostalCode(updateStationDto.getPostalCode());
        }

        return stationsRepository.save(station).getId();
    }


    public List<GetStationDto> getAllStations() {
        return stationsRepository.findAll()
                .stream()
                .map(Mappers::fromStationToGetStationDto)
                .collect(Collectors.toList());
    }

    public List<GetBrandDto> getAllBrands() {
        return brandsRepository.findAll()
                .stream()
                .map(Mappers::fromBrandToGetBrandDto)
                .collect(Collectors.toList());
    }
}
