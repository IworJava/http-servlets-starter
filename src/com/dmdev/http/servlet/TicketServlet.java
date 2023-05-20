package com.dmdev.http.servlet;

import com.dmdev.http.service.TicketService;
import com.dmdev.http.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/tickets")
public class TicketServlet extends HttpServlet {
    private final TicketService ticketService = TicketService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String flightIdStr = req.getParameter("flightId");

        if (flightIdStr != null) {
            Long flightId = Long.valueOf(flightIdStr);
            req.setAttribute("tickets", ticketService.findTicketsByFlightId(flightId));
        }
        req.getRequestDispatcher(JspHelper.getPath("tickets"))
                .forward(req, resp);
    }
}
