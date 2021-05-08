package com.app.sip.validator;

import com.app.sip.dto.UpdateStationDto;
import com.app.sip.validator.generic.AbstractValidator;
import com.app.sip.validator.generic.ValidatorFunctions;

import java.util.Map;
import java.util.Objects;

public class UpdateStationValidator extends AbstractValidator<UpdateStationDto> {
    @Override
    public Map<String, String> validate(UpdateStationDto updateStationDto) {
        if (Objects.isNull(updateStationDto.getId())) {
            errors.put("id", "id must be specified, can't update station without knowing which one.");
        }
        if (Objects.nonNull(updateStationDto.getName()) && !ValidatorFunctions.isNameCorrect(updateStationDto.getName())) {
            errors.put("name", "name must be a non-empty string");
        }

        if (Objects.nonNull(updateStationDto.getAddress()) && !ValidatorFunctions.isAddressCorrect(updateStationDto.getAddress())) {
            errors.put("address", "address must be a non-empty string");
        }

        if (Objects.nonNull(updateStationDto.getCity()) && !ValidatorFunctions.isCityCorrect(updateStationDto.getCity())) {
            errors.put("city", "city must be a non-empty string");
        }

        if (Objects.nonNull(updateStationDto.getPostalCode()) && !ValidatorFunctions.isPostalCodeCorrect(updateStationDto.getPostalCode())) {
            errors.put("postalCode", "postalCode must be a polish format postal-code(\\d{2}-\\d{3})");
        }

        if (Objects.nonNull(updateStationDto.getOpeningHours()) && !ValidatorFunctions.isOpeningHoursCorrect(updateStationDto.getOpeningHours())) {
            errors.put("openingHours", "openingHours  must be a non-empty string");
        }

        return errors;
    }
}
