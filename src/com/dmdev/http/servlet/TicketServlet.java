package com.dmdev.http.servlet;

import com.dmdev.http.dto.TicketDto;
import com.dmdev.http.service.TicketService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/tickets")
public class TicketServlet extends HttpServlet {
    private final TicketService ticketService = TicketService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        Long flightId = Long.valueOf(req.getParameter("flightId"));
        List<TicketDto> tickets = ticketService.findTicketsByFlightId(flightId);

        try (PrintWriter writer = resp.getWriter()) {
            writer.write("<H1>Список билетов:</H1>");
            writer.write("<ul>");
            tickets.forEach(t -> writer.write("<li>%s".formatted(t.getSeatNo())));
            writer.write("</ul>");
        }
    }
}
