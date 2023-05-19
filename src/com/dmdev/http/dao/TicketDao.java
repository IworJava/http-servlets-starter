package com.dmdev.http.dao;

import com.dmdev.http.entity.Ticket;
import com.dmdev.http.util.ConnectionManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TicketDao implements Dao<Long, Ticket> {
    private static final TicketDao INSTANCE = new TicketDao();

    public static TicketDao getInstance() {
        return INSTANCE;
    }

    private static final String FIND_BY_FLIGHT_ID = """
            SELECT id,
                    passenger_no,
                    passenger_name,
                    flight_id,
                    seat_no,
                    cost
            FROM ticket
            WHERE flight_id = ?;
            """;

    @SneakyThrows
    public List<Ticket> findByFlightId(Long flightId) {
        try (
                Connection connection = ConnectionManager.open();
                PreparedStatement statement = connection.prepareStatement(FIND_BY_FLIGHT_ID)
        ) {
            statement.setObject(1, flightId);
            ResultSet resultSet = statement.executeQuery();
            List<Ticket> tickets = new ArrayList<>();
            while (resultSet.next()) {
                tickets.add(buildTicket(resultSet));
            }
            return tickets;
        }
    }

    @Override
    public List<Ticket> findAll() {
        return null;
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public void update(Ticket entity) {

    }

    @Override
    public Ticket save(Ticket entity) {
        return null;
    }

    private Ticket buildTicket(ResultSet resultSet) {
        try {
            return new Ticket(
                    resultSet.getObject("id", Long.class),
                    resultSet.getObject("passenger_no", String.class),
                    resultSet.getObject("passenger_name", String.class),
                    resultSet.getObject("flight_id", Long.class),
                    resultSet.getObject("seat_no", String.class),
                    resultSet.getObject("cost", BigDecimal.class)
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
