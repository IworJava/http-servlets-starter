package com.dmdev.http.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class TicketDto {
    private Long id;
    private Long flightId;
    private String seatNo;
}
