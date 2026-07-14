package ir.reza.repository.impl;

import ir.reza.enums.EventStatus;
import ir.reza.model.Event;
import ir.reza.repository.EventRepository;
import ir.reza.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EventRepositoryImpl implements EventRepository {

    private static final String INSERT_EVENT =
            "INSERT INTO event(title, location, capacity, reserved_count, ticket_price, status) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_EVENT =
            "UPDATE event SET title=?, location=?, capacity=?, reserved_count=?, ticket_price=?, status=? WHERE id=?";

    private static final String DELETE_EVENT =
            "DELETE FROM event WHERE id=?";

    private static final String FIND_BY_ID =
            "SELECT * FROM event WHERE id=?";

    private static final String FIND_ALL =
            "SELECT * FROM event";


    @Override
    public void save(Event event) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_EVENT)) {

            statement.setString(1, event.getTitle());
            statement.setString(2, event.getLocation());
            statement.setInt(3, event.getCapacity());
            statement.setInt(4, event.getReservedCount());
            statement.setBigDecimal(5, event.getTicketPrice());
            statement.setString(6, event.getStatus().name());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Event event) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_EVENT)) {

            statement.setString(1, event.getTitle());
            statement.setString(2, event.getLocation());
            statement.setInt(3, event.getCapacity());
            statement.setInt(4, event.getReservedCount());
            statement.setBigDecimal(5, event.getTicketPrice());
            statement.setString(6, event.getStatus().name());
            statement.setLong(7, event.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_EVENT)) {

            statement.setLong(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Event> findById(Long id) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Event event = new Event(
                        resultSet.getLong("id"),
                        EventStatus.valueOf(resultSet.getString("status")),
                        resultSet.getBigDecimal("ticket_price"),
                        resultSet.getInt("reserved_count"),
                        resultSet.getInt("capacity"),
                        resultSet.getString("location"),
                        resultSet.getString("title")
                );

                return Optional.of(event);
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Event> findAll() {

        List<Event> events = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Event event = new Event(
                        resultSet.getLong("id"),
                        EventStatus.valueOf(resultSet.getString("status")),
                        resultSet.getBigDecimal("ticket_price"),
                        resultSet.getInt("reserved_count"),
                        resultSet.getInt("capacity"),
                        resultSet.getString("location"),
                        resultSet.getString("title")
                );

                events.add(event);
            }

            return events;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
