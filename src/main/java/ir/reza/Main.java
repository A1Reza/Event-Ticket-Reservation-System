package ir.reza;

import ir.reza.enums.EventStatus;
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

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        EventRepository eventRepository = new EventRepositoryImpl();
        ReservationRepository reservationRepository =
                new ReservationRepositoryImpl();

        EventService eventService =
                new EventService(eventRepository);

        ReservationService reservationService =
                new ReservationService(
                        reservationRepository,
                        eventRepository
                );

        ReportService reportService =
                new ReportService(
                        eventRepository,
                        reservationRepository
                );


        while (true) {

            try {

                menu();

                int choice = Integer.parseInt(scanner.nextLine());


                switch (choice) {

                    case 1 -> createEvent(eventService);

                    case 2 ->
                            eventService.getAllEvents()
                                    .forEach(System.out::println);


                    case 3 ->
                            updateEvent(eventService);


                    case 4 -> {

                        System.out.print("Event id: ");
                        Long id = Long.parseLong(scanner.nextLine());

                        eventService.cancelEvent(id);

                        System.out.println("Event cancelled.");
                    }


                    case 5 ->
                            createReservation(reservationService);


                    case 6 -> {

                        System.out.print("Reservation id: ");
                        Long id =
                                Long.parseLong(scanner.nextLine());

                        reservationService.cancelReservation(id);

                        System.out.println("Reservation cancelled.");
                    }


                    case 7 ->
                            reservationService.getAllReservations()
                                    .forEach(System.out::println);


                    case 8 ->
                            reports(reportService);


                    case 9 -> {
                        System.out.println("Exit...");
                        return;
                    }


                    default ->
                            System.out.println("Invalid choice.");
                }


            } catch (Exception e) {

                System.out.println(
                        "Error: " + e.getMessage()
                );
            }
        }
    }


    private static void menu() {

        System.out.println("""
                
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



    private static void createEvent(EventService service) {

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
                EventStatus.ACTIVE,
                price,
                0,
                capacity,
                location,
                title
        );


        service.createEvent(event);

        System.out.println("Created.");
    }



    private static void updateEvent(EventService service) {

        System.out.print("Event id: ");
        Long id =
                Long.parseLong(scanner.nextLine());


        Event event =
                service.getEventById(id);


        System.out.print("New title: ");
        event.setTitle(scanner.nextLine());


        System.out.print("New location: ");
        event.setLocation(scanner.nextLine());


        System.out.print("New capacity: ");
        event.setCapacity(
                Integer.parseInt(scanner.nextLine())
        );


        System.out.print("New price: ");
        event.setTicketPrice(
                new BigDecimal(scanner.nextLine())
        );


        service.updateEvent(event);

        System.out.println("Updated.");
    }




    private static void createReservation(
            ReservationService service) {


        System.out.print("Customer name: ");
        String name = scanner.nextLine();


        System.out.print("Phone: ");
        String phone = scanner.nextLine();


        System.out.print("Event id: ");
        Long eventId =
                Long.parseLong(scanner.nextLine());


        System.out.print("Ticket count: ");
        Integer count =
                Integer.parseInt(scanner.nextLine());


        Reservation reservation =
                new Reservation(
                        LocalDate.now(),
                        null,
                        count,
                        eventId,
                        phone,
                        name
                );


        service.createReservation(reservation);


        System.out.println("Reservation created.");
    }



    private static void reports(
            ReportService service) {


        System.out.println(
                "Active events: "
                        + service.getTotalActiveEvents()
        );


        System.out.println(
                "Average price: "
                        + service.getAverageTicketPrice()
        );


        System.out.println(
                "Most expensive: "
                        + service.getMostExpensiveEvent()
        );


        System.out.println("Active reservations:");

        service.getActiveReservations()
                .forEach(System.out::println);


        System.out.println("Full events:");

        service.getFullyBookedEvents()
                .forEach(System.out::println);
    }
}