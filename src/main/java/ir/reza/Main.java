package ir.reza;

import ir.reza.exception.BusinessException;
import ir.reza.model.Event;
import ir.reza.model.Reservation;
import ir.reza.repository.EventRepository;
import ir.reza.repository.ReservationRepository;
import ir.reza.repository.impl.EventRepositoryImpl;
import ir.reza.repository.impl.ReservationRepositoryImpl;
import ir.reza.service.EventService;
import ir.reza.service.ReportService;
import ir.reza.service.ReservationService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        EventRepository eventRepository = new EventRepositoryImpl();
        ReservationRepository reservationRepository = new ReservationRepositoryImpl();

        EventService eventService = new EventService(eventRepository);
        ReservationService reservationService =
                new ReservationService(reservationRepository, eventRepository);

        ReportService reportService =
                new ReportService(eventRepository, reservationRepository);


        while (true) {

            try {

                showMenu();

                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {

                    case 1:
                        createEvent(eventService);
                        break;

                    case 2:
                        eventService.getAllEvents()
                                .forEach(System.out::println);
                        break;

                    case 3:
                        System.out.println("Update Event - add later");
                        break;

                    case 4:
                        System.out.print("Event id: ");
                        Long eventId = Long.parseLong(scanner.nextLine());

                        eventService.cancelEvent(eventId);

                        System.out.println("Event cancelled.");
                        break;

                    case 5:
                        createReservation(reservationService);
                        break;

                    case 6:
                        System.out.print("Reservation id: ");

                        Long reservationId =
                                Long.parseLong(scanner.nextLine());

                        reservationService.cancelReservation(reservationId);

                        System.out.println("Reservation cancelled.");
                        break;

                    case 7:
                        reservationService.getAllReservations()
                                .forEach(System.out::println);
                        break;

                    case 8:
                        showReports(reportService);
                        break;

                    case 9:
                        System.out.println("Goodbye!");
                        return;

                    default:
                        System.out.println("Invalid option.");
                }


            } catch (BusinessException e) {

                System.out.println("Error: " + e.getMessage());

            } catch (Exception e) {

                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
    }


    private static void showMenu() {

        System.out.println("""
                
                ===== Event Ticket Reservation System =====
                
                1. Create Event
                2. Show All Events
                3. Update Event
                4. Cancel Event
                5. Create Reservation
                6. Cancel Reservation
                7. Show All Reservations
                8. Reports
                9. Exit
                
                Choose:
                """);
    }


    private static void createEvent(EventService eventService) {

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Location: ");
        String location = scanner.nextLine();

        System.out.print("Capacity: ");
        Integer capacity =
                Integer.parseInt(scanner.nextLine());

        System.out.print("Ticket price: ");
        BigDecimal price =
                new BigDecimal(scanner.nextLine());


        Event event = new Event(
                null,
                price,
                0,
                capacity,
                location,
                title
        );


        eventService.createEvent(event);

        System.out.println("Event created.");
    }


    private static void createReservation(
            ReservationService reservationService) {


        System.out.print("Customer name: ");
        String name = scanner.nextLine();


        System.out.print("Customer phone: ");
        String phone = scanner.nextLine();


        System.out.print("Event id: ");
        Long eventId =
                Long.parseLong(scanner.nextLine());


        System.out.print("Ticket count: ");
        Integer count =
                Integer.parseInt(scanner.nextLine());


        Reservation reservation = new Reservation(
                LocalDate.now(),
                null,
                count,
                eventId,
                phone,
                name
        );


        reservationService.createReservation(reservation);


        System.out.println("Reservation created.");
    }


    private static void showReports(
            ReportService reportService) {


        System.out.println(
                "Active events: "
                        + reportService.getTotalActiveEvents()
        );


        System.out.println(
                "Average ticket price: "
                        + reportService.getAverageTicketPrice()
        );


        System.out.println(
                "Most expensive event: "
                        + reportService.getMostExpensiveEvent()
        );


        System.out.println("Active reservations:");

        reportService.getActiveReservations()
                .forEach(System.out::println);


        System.out.println("Fully booked events:");

        reportService.getFullyBookedEvents()
                .forEach(System.out::println);
    }
}