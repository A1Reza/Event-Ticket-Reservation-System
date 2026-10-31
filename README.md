# 🎟️ Event Ticket Reservation System

A Java-based console application for managing events and ticket reservations. The system allows administrators to create and manage events, while customers can reserve and cancel tickets for available events.

This project focuses on implementing clean application architecture, database interaction, business logic, and Java Stream API.

## 🚀 Features

### 🎪 Event Management
- Create new events.
- Display all events.
- Update event information.
- Cancel events.
- Find events by ID.
- Validate event information before saving or updating.

### 🎫 Reservation Management
- Create ticket reservations.
- Display all reservations.
- Find reservations by ID.
- Cancel existing reservations.
- Automatically update the reserved ticket count.
- Prevent reservations when an event is cancelled or fully booked.

### 📊 Stream API Reports
Generate reports using Java Stream API:

- Total number of active events.
- Most expensive event.
- Average ticket price of all events.
- List of active reservations.
- List of fully booked events.

### 🖥️ Console Application
The application provides an interactive menu for managing events, reservations, and reports.

The program continues running even when exceptions occur and displays meaningful error messages instead of terminating unexpectedly.

## 🛠️ Technologies

| Technology | Purpose |
|---|---|
| Java | Core application development |
| JDBC | Database connectivity |
| PostgreSQL | Relational database |
| SQL | Database operations |
| Java Stream API | Data processing and reporting |
| Enums | Managing event and reservation statuses |
| Git | Version control |
| GitHub | Source code hosting |

## 🏗️ Architecture & Design Patterns

The application follows a layered architecture to separate database operations, business logic, and data presentation.

**Repository Layer**

Responsible for database operations using JDBC, including saving, updating, deleting, and retrieving records.

**Generic Repository Pattern**

Provides reusable CRUD operations through a generic repository interface, reducing code duplication and improving maintainability.

**Service Layer**

Handles business logic and validation, including event management, reservation rules, ticket availability, and cancellation processes.

**Model Layer**

Represents the application's main entities:

- `Event`
- `Reservation`

**Enums**

Java Enums are used to manage application statuses:

- `EventStatus`: ACTIVE, CANCELLED
- `ReservationStatus`: ACTIVE, CANCELLED

## 🗄️ Database Design

The application uses PostgreSQL with two main tables.

### Event

| Column | Type |
|---|---|
| id | BIGSERIAL |
| title | VARCHAR |
| location | VARCHAR |
| capacity | INTEGER |
| reserved_count | INTEGER |
| ticket_price | DECIMAL |
| status | VARCHAR |

### Reservation

| Column | Type |
|---|---|
| id | BIGSERIAL |
| customer_name | VARCHAR |
| customer_phone | VARCHAR |
| event_id | BIGINT |
| ticket_count | INTEGER |
| reservation_date | DATE |
| status | VARCHAR |

Status values are stored as VARCHAR in the database and managed using Java Enums within the application.

## 🔄 Business Rules

### Event Validation
- Event title cannot be empty.
- Event capacity cannot be negative.
- Ticket price cannot be negative.

### Reservation Validation
- The requested event must exist.
- Only active events can receive reservations.
- Requested tickets must not exceed the remaining capacity.
- Successful reservations increase the event's reserved ticket count.

### Cancellation Rules
- Cancelling a reservation changes its status to CANCELLED.
- Cancelled reservations decrease the event's reserved ticket count.
- Cancelling an event changes its status to CANCELLED.
- Existing reservations remain unchanged when an event is cancelled.
- New reservations cannot be created for cancelled events.

## ⚠️ Exception Handling

The application uses custom exceptions to handle business errors and invalid operations.

Examples include:

- `BusinessException`
- `EventNotFoundException`
- `ReservationNotFoundException`
- `CapacityExceededException`
- `EventCancelledException`
- `InvalidDataException`

Meaningful error messages are displayed to help users understand why an operation failed.

## 🔌 JDBC & Database Connectivity

JDBC (Java Database Connectivity) is a standard Java API that allows Java applications to communicate with relational databases.

In this project, JDBC is used to establish connections to PostgreSQL, execute SQL statements, and retrieve or modify database records.

Important JDBC components include:

- **DriverManager:** Establishes database connections.
- **Connection:** Represents an active database connection.
- **PreparedStatement:** Executes parameterized SQL statements and helps prevent SQL Injection.
- **ResultSet:** Retrieves records returned by SQL queries.

## 📈 Java Stream API

Java Stream API is used to process collections of events and reservations in a clean and readable way.

It helps implement reporting operations such as filtering active events, finding the most expensive event, calculating average ticket prices, and identifying fully booked events.

## 🎯 Learning Objectives

This project demonstrates practical knowledge of:

- JDBC and PostgreSQL integration.
- Repository Pattern and Generic Repository.
- Service Layer architecture.
- Object-Oriented Programming (OOP).
- Java Enums.
- Java Stream API.
- Custom Exception Handling.
- Business Logic Validation.
- Database Operations.
- Git and Conventional Commits.

---

**Developed with ☕ Java and ❤️**
