package com.app.sip.validator;

import com.app.sip.dto.CreateStationDto;
import com.app.sip.validator.generic.AbstractValidator;
import com.app.sip.validator.generic.ValidatorFunctions;

import java.util.Map;
import java.util.Objects;

public class CreateStationValidator extends AbstractValidator<CreateStationDto> {
    @Override
    public Map<String, String> validate(CreateStationDto createStationDto) {
        if (!ValidatorFunctions.isNameCorrect(createStationDto.getName())) {
            errors.put("name", "name must be a non-empty string");
        }

        if (!ValidatorFunctions.isAddressCorrect(createStationDto.getAddress())) {
            errors.put("address", "address must be a non-empty string");
        }

        if (!ValidatorFunctions.isCityCorrect(createStationDto.getCity())) {
            errors.put("city", "city must be a non-empty string");
        }

        if (!ValidatorFunctions.isPostalCodeCorrect(createStationDto.getPostalCode())) {
            errors.put("postalCode", "postalCode must be a polish format postal-code(\\d{2}-\\d{3})");
        }

        if (Objects.isNull(createStationDto.getHasFuel95())) {
            errors.put("hasFuel95", "hasFuel95 must be non-null boolean value");
        }

        if (Objects.isNull(createStationDto.getHasFuel98())) {
            errors.put("hasFuel98", "hasFuel98 must be non-null boolean value");
        }

        if (Objects.isNull(createStationDto.getHasFuel95())) {
            errors.put("hasFuelDiesel", "hasFuelDiesel must be non-null boolean value");
        }

        if (Objects.isNull(createStationDto.getHasFuel95())) {
            errors.put("hasFuelLpg", "hasFuelLpg must be non-null boolean value");
        }

        if (Objects.isNull(createStationDto.getBrandId())) {
            errors.put("brandId", "brandId can't be null");
        }

        if (!ValidatorFunctions.isOpeningHoursCorrect(createStationDto.getOpeningHours())) {
            errors.put("openingHours", "openingHours  must be a non-empty string");
        }

        return errors;
    }
}