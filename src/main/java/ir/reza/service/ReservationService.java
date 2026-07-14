package ir.reza.service;

import ir.reza.enums.EventStatus;
import ir.reza.enums.ReservationStatus;
import ir.reza.exception.CapacityExceededException;
import ir.reza.exception.EventCancelledException;
import ir.reza.exception.EventNotFoundException;
import ir.reza.exception.ReservationNotFoundException;
import ir.reza.model.Event;
import ir.reza.model.Reservation;
import ir.reza.repository.EventRepository;
import ir.reza.repository.ReservationRepository;

import java.time.LocalDate;
import java.util.List;

public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final EventRepository eventRepository;

    public ReservationService(ReservationRepository reservationRepository,
                              EventRepository eventRepository) {
        this.reservationRepository = reservationRepository;
        this.eventRepository = eventRepository;
    }

    public void createReservation(Reservation reservation) {

        Event event = eventRepository.findById(reservation.getEventId())
                .orElseThrow(() ->
                        new EventNotFoundException("Event not found."));

        if (event.getStatus() == EventStatus.CANCELLED) {
            throw new EventCancelledException("Event is cancelled.");
        }

        int remainingCapacity =
                event.getCapacity() - event.getReservedCount();

        if (reservation.getTicketCount() > remainingCapacity) {
            throw new CapacityExceededException("Not enough remaining capacity.");
        }

        reservation.setReservationDate(LocalDate.now());
        reservation.setStatus(ReservationStatus.ACTIVE);

        reservationRepository.save(reservation);

        event.setReservedCount(
                event.getReservedCount() + reservation.getTicketCount());

        eventRepository.update(event);
    }

    public void cancelReservation(Long reservationId) {

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() ->
                        new ReservationNotFoundException("Reservation not found."));

        if (reservation.getStatus() == ReservationStatus.CANCELLED) {
            return;
        }

        Event event = eventRepository.findById(reservation.getEventId())
                .orElseThrow(() ->
                        new EventNotFoundException("Event not found."));

        reservation.setStatus(ReservationStatus.CANCELLED);

        reservationRepository.update(reservation);

        event.setReservedCount(
                event.getReservedCount() - reservation.getTicketCount());

        eventRepository.update(event);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long id) {

        return reservationRepository.findById(id)
                .orElseThrow(() ->
                        new ReservationNotFoundException("Reservation not found."));
    }

    public void updateReservation(Reservation reservation) {

        reservationRepository.findById(reservation.getId())
                .orElseThrow(() ->
                        new ReservationNotFoundException("Reservation not found."));

        reservationRepository.update(reservation);
    }
}