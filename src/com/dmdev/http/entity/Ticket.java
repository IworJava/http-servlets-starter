package com.dmdev.http.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Ticket {
        Long id;
        String passengerNo;
        String passengerName;
        Long flightId;
        String seatNo;
        BigDecimal cost;
}
