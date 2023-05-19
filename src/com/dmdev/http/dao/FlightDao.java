package com.dmdev.http.dao;

import com.dmdev.http.entity.Flight;
import com.dmdev.http.entity.FlightStatus;
import com.dmdev.http.util.ConnectionManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FlightDao implements Dao<Long, Flight> {
    private static final FlightDao INSTANCE = new FlightDao();

    public static FlightDao getInstance() {
        return INSTANCE;
    }

    private static final String FIND_ALL = """
            SELECT id,
                flight_no,
                departure_date,
                departure_airport_code,
                arrival_date,
                arrival_airport_code,
                aircraft_id,
                status
            FROM flight
            """;

    @Override
    @SneakyThrows
    public List<Flight> findAll() {
        try (
                Connection connection = ConnectionManager.open();
                PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Flight> flights = new ArrayList<>();

            while (resultSet.next()) {
                flights.add(buildFlight(resultSet));
            }
            return flights;
        }
    }

    @Override
    public Optional<Flight> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public void update(Flight entity) {

    }

    @Override
    public Flight save(Flight entity) {
        return null;
    }

    private Flight buildFlight(ResultSet resultSet) throws SQLException {
        return new Flight(
                resultSet.getLong("id"),
                resultSet.getString("flight_no"),
                resultSet.getTimestamp("departure_date").toLocalDateTime(),
                resultSet.getString("departure_airport_code"),
                resultSet.getTimestamp("arrival_date").toLocalDateTime(),
                resultSet.getString("arrival_airport_code"),
                resultSet.getInt("aircraft_id"),
                FlightStatus.valueOf(resultSet.getString("status"))
        );
    }
}
