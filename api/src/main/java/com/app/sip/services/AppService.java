package com.app.sip.services;

import com.app.sip.dto.*;
import com.app.sip.mappers.Mappers;
import com.app.sip.model.Comment;
import com.app.sip.repositories.BrandsRepository;
import com.app.sip.repositories.CommentsRepository;
import com.app.sip.repositories.StationsRepository;
import com.app.sip.validator.CreateStationValidator;
import com.app.sip.validator.UpdateStationValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AppService {
    private final BrandsRepository brandsRepository;
    private final StationsRepository stationsRepository;
    private final CommentsRepository commentsRepository;

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

        var station = stationsRepository.findById(updateStationDto.getId()).orElseThrow(() -> {
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
        if (Objects.nonNull(updateStationDto.getPriceFuel95())) {
            station.setPriceFuel95(updateStationDto.getPriceFuel95());
        }
        if (Objects.nonNull(updateStationDto.getPriceFuel98())) {
            station.setPriceFuel98(updateStationDto.getPriceFuel98());
        }
        if (Objects.nonNull(updateStationDto.getPriceFuelDiesel())) {
            station.setPriceFuelDiesel(updateStationDto.getPriceFuelDiesel());
        }
        if (Objects.nonNull(updateStationDto.getPriceFuelLpg())) {
            station.setPriceFuelLpg(updateStationDto.getPriceFuelLpg());
        }
        if (Objects.nonNull(updateStationDto.getPostalCode())) {
            station.setPostalCode(updateStationDto.getPostalCode());
        }
        if (Objects.nonNull(updateStationDto.getLatitude())) {
            station.setLatitude(updateStationDto.getLatitude());
        }
        if (Objects.nonNull(updateStationDto.getLongitude())) {
            station.setLongitude(updateStationDto.getLongitude());
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

    private static Double calculateDistance(Double latitude1, Double longitude1,
                                            Double latitude2, Double longitude2) {
        // optimized version -> /27928/calculate-distance-between-two-latitude-longitude-points-haversine-formula
        var p = 0.017453292519943295; // Math.PI / 180
        var a = 0.5 - (Math.cos((latitude2 - latitude1) * p) / 2) +
                (Math.cos(latitude1 * p) * Math.cos(latitude2 * p) *
                        (1 - Math.cos((longitude2 - longitude1) * p)) / 2);

        return 12742 * Math.asin(Math.sqrt(a)); // 2 * R; R = 6371 km
    }

    public List<GetStationDto> getNearest(Double latitude, Double longitude) {
        return stationsRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(s -> calculateDistance(latitude, longitude, s.getLatitude(), s.getLongitude())))
                .limit(10)
                .map(Mappers::fromStationToGetStationDto)
                .collect(Collectors.toList());
    }

    public Long addStationComment(Long stationId, String comment) {
        stationsRepository.findById(stationId).orElseThrow(() -> {
            throw new RuntimeException("AppService: addStationComment: no station with id: " + stationId);
        });
        return commentsRepository.save(Comment.builder().comment(comment).stationId(stationId).build()).getId();
    }

    public List<GetCommentDto> getStationComments(Long stationId) {
        stationsRepository.findById(stationId).orElseThrow(() -> {
            throw new RuntimeException("AppService: getStationComments: no station with id: " + stationId);
        });
        return commentsRepository.findAllByStationId(stationId).stream()
                .map(comment -> new GetCommentDto(comment.getComment()))
                .collect(Collectors.toList());
    }

    public Long addStationRating(Long stationId, Integer rating) {
        if (rating < 1 || rating > 5) {
            throw new RuntimeException("AppService: addStationRating: bad station rating: " + rating);
        }
        var station = stationsRepository.findById(stationId).orElseThrow(() -> {
            throw new RuntimeException("AppService: addStationRating: no station with id: " + stationId);
        });

        station.setRatingCount(station.getRatingCount() +1);
        station.setSumOfRating(station.getSumOfRating() + rating);
        return stationsRepository.save(station).getId();
    }
}
