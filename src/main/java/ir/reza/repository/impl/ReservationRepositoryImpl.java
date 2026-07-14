package ir.reza.repository.impl;

import ir.reza.enums.ReservationStatus;
import ir.reza.model.Reservation;
import ir.reza.repository.ReservationRepository;
import ir.reza.util.DBConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationRepositoryImpl implements ReservationRepository {

    private static final String INSERT_RESERVATION =
            "INSERT INTO reservation(customer_name, customer_phone, event_id, ticket_count, reservation_date, status) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_RESERVATION =
            "UPDATE reservation SET customer_name=?, customer_phone=?, event_id=?, ticket_count=?, reservation_date=?, status=? WHERE id=?";

    private static final String DELETE_RESERVATION =
            "DELETE FROM reservation WHERE id=?";

    private static final String FIND_BY_ID =
            "SELECT * FROM reservation WHERE id=?";

    private static final String FIND_ALL =
            "SELECT * FROM reservation";

    @Override
    public void save(Reservation reservation) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_RESERVATION)) {

            statement.setString(1, reservation.getCustomerName());
            statement.setString(2, reservation.getCustomerPhone());
            statement.setLong(3, reservation.getEventId());
            statement.setInt(4, reservation.getTicketCount());
            statement.setDate(5, Date.valueOf(reservation.getReservationDate()));
            statement.setString(6, reservation.getStatus().name());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Reservation reservation) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_RESERVATION)) {

            statement.setString(1, reservation.getCustomerName());
            statement.setString(2, reservation.getCustomerPhone());
            statement.setLong(3, reservation.getEventId());
            statement.setInt(4, reservation.getTicketCount());
            statement.setDate(5, Date.valueOf(reservation.getReservationDate()));
            statement.setString(6, reservation.getStatus().name());
            statement.setLong(7, reservation.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RESERVATION)) {

            statement.setLong(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Reservation> findById(Long id) {

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Reservation reservation = new Reservation(
                        resultSet.getLong("id"),
                        resultSet.getDate("reservation_date").toLocalDate(),
                        ReservationStatus.valueOf(resultSet.getString("status")),
                        resultSet.getInt("ticket_count"),
                        resultSet.getLong("event_id"),
                        resultSet.getString("customer_phone"),
                        resultSet.getString("customer_name")
                );

                return Optional.of(reservation);
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Reservation> findAll() {

        List<Reservation> reservations = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Reservation reservation = new Reservation(
                        resultSet.getLong("id"),
                        resultSet.getDate("reservation_date").toLocalDate(),
                        ReservationStatus.valueOf(resultSet.getString("status")),
                        resultSet.getInt("ticket_count"),
                        resultSet.getLong("event_id"),
                        resultSet.getString("customer_phone"),
                        resultSet.getString("customer_name")
                );

                reservations.add(reservation);
            }

            return reservations;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}