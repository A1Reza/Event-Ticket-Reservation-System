package ir.reza.service;

import ir.reza.enums.EventStatus;
import ir.reza.enums.ReservationStatus;
import ir.reza.model.Event;
import ir.reza.model.Reservation;
import ir.reza.repository.EventRepository;
import ir.reza.repository.ReservationRepository;

import java.util.Comparator;
import java.util.List;

public class ReportService {

    private final EventRepository eventRepository;
    private final ReservationRepository reservationRepository;

    public ReportService(EventRepository eventRepository,
                         ReservationRepository reservationRepository) {

        this.eventRepository = eventRepository;
        this.reservationRepository = reservationRepository;
    }


    public long getTotalActiveEvents() {

        return eventRepository.findAll()
                .stream()
                .filter(event -> event.getStatus() == EventStatus.ACTIVE)
                .count();
    }


    public Event getMostExpensiveEvent() {

        return eventRepository.findAll()
                .stream()
                .max(Comparator.comparing(Event::getTicketPrice))
                .orElse(null);
    }


    public double getAverageTicketPrice() {

        return eventRepository.findAll()
                .stream()
                .mapToDouble(event -> event.getTicketPrice().doubleValue())
                .average()
                .orElse(0);
    }


    public List<Reservation> getActiveReservations() {

        return reservationRepository.findAll()
                .stream()
                .filter(reservation ->
                        reservation.getStatus() == ReservationStatus.ACTIVE)
                .toList();
    }


    public List<Event> getFullyBookedEvents() {

        return eventRepository.findAll()
                .stream()
                .filter(event ->
                        event.getReservedCount().equals(event.getCapacity()))
                .toList();
    }
}