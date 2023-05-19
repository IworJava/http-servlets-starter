package com.dmdev.http.service;

import com.dmdev.http.dao.TicketDao;
import com.dmdev.http.dto.TicketDto;

import java.util.List;
import java.util.stream.Collectors;

public class TicketService {
    private static final TicketService INSTANCE = new TicketService();
    private final TicketDao ticketDao = TicketDao.getInstance();

    private TicketService() {
    }

    public static TicketService getInstance() {
        return INSTANCE;
    }

    public List<TicketDto> findTicketsByFlightId(Long flightId) {
        return ticketDao.findByFlightId(flightId).stream()
                .map(ticket -> new TicketDto(
                        ticket.getId(),
                        ticket.getFlightId(),
                        ticket.getSeatNo()
                ))
                .collect(Collectors.toList());
    }
}
