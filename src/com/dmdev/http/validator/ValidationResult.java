package com.dmdev.http.validator;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {
    @Getter
    private final List<Error> errors = new ArrayList<>();

    public boolean isValid() {
        return errors.isEmpty();
    }

    public void add(Error error) {
        errors.add(error);
    }
}
