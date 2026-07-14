package ir.reza.model;

import ir.reza.enums.ReservationStatus;

import java.time.LocalDate;

public class Reservation {

    private Long id;
    private String customerName;
    private String customerPhone;
    private Long eventId;
    private Integer ticketCount;
    private LocalDate reservationDate;
    private ReservationStatus status;

    public Reservation() {

    }

    public Reservation(Long id, LocalDate reservationDate, ReservationStatus status, Integer ticketCount, Long eventId, String customerPhone, String customerName) {
        this.id = id;
        this.reservationDate = reservationDate;
        this.status = status;
        this.ticketCount = ticketCount;
        this.eventId = eventId;
        this.customerPhone = customerPhone;
        this.customerName = customerName;
    }

    public Reservation(LocalDate reservationDate, ReservationStatus status, Integer ticketCount, Long eventId, String customerPhone, String customerName) {
        this.reservationDate = reservationDate;
        this.status = status;
        this.ticketCount = ticketCount;
        this.eventId = eventId;
        this.customerPhone = customerPhone;
        this.customerName = customerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Integer getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(Integer ticketCount) {
        this.ticketCount = ticketCount;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", eventId=" + eventId +
                ", ticketCount=" + ticketCount +
                ", reservationDate=" + reservationDate +
                ", status=" + status +
                '}';
    }
}
