package com.app.sip.validator.generic;

import java.util.Map;

public interface Validator<T> {
    Map<String, String> validate(T item);
    boolean hasErrors();
}
