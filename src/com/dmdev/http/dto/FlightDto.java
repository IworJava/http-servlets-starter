package com.dmdev.http.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class FlightDto {
    private final Long id;
    private final String description;
}
