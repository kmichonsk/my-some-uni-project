package com.app.sip.validator.generic;

import java.util.Objects;

public interface ValidatorFunctions {
    private static boolean isValidString(String string) {
        return Objects.nonNull(string) && !string.isEmpty() && !string.isBlank();
    }

    static boolean isNameCorrect(String name) {
        return isValidString(name);
    }

    static boolean isAddressCorrect(String address) {
        return isValidString(address);
    }

    static boolean isCityCorrect(String city) {
        return isValidString(city);
    }

    static boolean isPostalCodeCorrect(String postalCode) {
        return isValidString(postalCode) && postalCode.matches("\\d{2}-\\d{3}");
    }

    static boolean isOpeningHoursCorrect(String openingHours) {
        return isValidString(openingHours);
    }
}
