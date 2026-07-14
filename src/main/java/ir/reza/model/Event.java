package ir.reza.model;

import ir.reza.enums.EventStatus;

import java.math.BigDecimal;

public class Event {
    private Long id;
    private String title;
    private String location;
    private Integer capacity;
    private Integer reservedCount;
    private BigDecimal ticketPrice;
    private EventStatus status;

    public Event() {

    }

    public Event(Long id, EventStatus status, BigDecimal ticketPrice, Integer reservedCount, Integer capacity, String location, String title) {
        this.id = id;
        this.status = status;
        this.ticketPrice = ticketPrice;
        this.reservedCount = reservedCount;
        this.capacity = capacity;
        this.location = location;
        this.title = title;
    }

    public Event(EventStatus status, BigDecimal ticketPrice, Integer reservedCount, Integer capacity, String location, String title) {
        this.status = status;
        this.ticketPrice = ticketPrice;
        this.reservedCount = reservedCount;
        this.capacity = capacity;
        this.location = location;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public Integer getReservedCount() {
        return reservedCount;
    }

    public void setReservedCount(Integer reservedCount) {
        this.reservedCount = reservedCount;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", capacity=" + capacity +
                ", reservedCount=" + reservedCount +
                ", ticketPrice=" + ticketPrice +
                ", status=" + status +
                '}';
    }
}
