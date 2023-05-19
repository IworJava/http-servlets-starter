package com.dmdev.http.service;

import com.dmdev.http.dao.FlightDao;
import com.dmdev.http.dto.FlightDto;
import com.dmdev.http.entity.Flight;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlightService {
    private static final FlightService INSTANCE = new FlightService();
    private final FlightDao flightDao = FlightDao.getInstance();

    private FlightService() {
    }

    public List<FlightDto> findAll() {
        return flightDao.findAll().stream()
                .map(flight -> new FlightDto(
                        flight.getId(),
                        "%s - %s - %s".formatted(
                                flight.getDepartureAirportCode(),
                                flight.getArrivalAirportCode(),
                                flight.getStatus()
                        )
                ))
                .collect(Collectors.toList());
    }
    public List<FlightDto> findAll1() {
        List<Flight> flights = flightDao.findAll();
        ArrayList<FlightDto> flightsDto = new ArrayList<>();
        for (Flight flight : flights) {
            flightsDto.add(convertEntityToDto(flight));
        }
        return flightsDto;
    }

    private FlightDto convertEntityToDto(Flight flight) {
        String description = String.format("%s - %s - %s",
                flight.getDepartureAirportCode(),
                flight.getArrivalAirportCode(),
                flight.getStatus());
        return new FlightDto(flight.getId(), description);
    }

    public static FlightService getInstance() {
        return INSTANCE;
    }
}
