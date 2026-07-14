package ir.reza.service;

import ir.reza.enums.EventStatus;
import ir.reza.exception.EventNotFoundException;
import ir.reza.exception.InvalidDataException;
import ir.reza.model.Event;
import ir.reza.repository.EventRepository;

import java.math.BigDecimal;
import java.util.List;

public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void createEvent(Event event) {

        validateEvent(event);

        if (event.getReservedCount() == null) {
            event.setReservedCount(0);
        }

        if (event.getStatus() == null) {
            event.setStatus(EventStatus.ACTIVE);
        }

        eventRepository.save(event);
    }

    public void updateEvent(Event event) {

        validateEvent(event);

        eventRepository.findById(event.getId())
                .orElseThrow(() ->
                        new EventNotFoundException("Event not found."));

        eventRepository.update(event);
    }

    public void cancelEvent(Long id) {

        Event event = eventRepository.findById(id)
                .orElseThrow(() ->
                        new EventNotFoundException("Event not found."));

        event.setStatus(EventStatus.CANCELLED);

        eventRepository.update(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(Long id) {

        return eventRepository.findById(id)
                .orElseThrow(() ->
                        new EventNotFoundException("Event not found."));
    }

    public void deleteEvent(Long id) {

        eventRepository.findById(id)
                .orElseThrow(() ->
                        new EventNotFoundException("Event not found."));

        eventRepository.delete(id);
    }

    private void validateEvent(Event event) {

        if (event.getTitle() == null || event.getTitle().isBlank()) {
            throw new InvalidDataException("Title cannot be empty.");
        }

        if (event.getCapacity() == null || event.getCapacity() < 0) {
            throw new InvalidDataException("Capacity cannot be negative.");
        }

        if (event.getTicketPrice() == null ||
                event.getTicketPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidDataException("Ticket price cannot be negative.");
        }
    }
}