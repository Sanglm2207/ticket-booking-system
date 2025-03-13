# Concert Ticket Booking System

## Overview
The **Concert Ticket Booking System** is a backend service designed to facilitate online ticket booking for events and shows. It provides functionalities for user authentication, booking management, payments, and role-based access control.

## Technologies Used
- **Java 17**: Modern Java features for improved performance and security.
- **Spring Boot**: Simplifies development and deployment.
- **MySQL**: Relational database for structured data storage.
- **JWT (JSON Web Token)**: Secure authentication and authorization.
- **Spring Security**: Ensures system security and user authentication.
- **Kafka**: Message queue for asynchronous processing.
- **Redis**: Caching mechanism for performance optimization.

## Database Schema
The database schema follows a structured Entity-Relationship Diagram (ERD) to manage ticket bookings efficiently. The key entities include:

- **Users**: Stores user information such as name, email, password, and role.
- **Roles & Permissions**: Implements role-based access control (RBAC).
- **Shows**: Contains details of available shows, including name, location, and schedule.
- **Tickets**: Stores ticket details like seat number, price, and status.
- **Bookings**: Manages user bookings with status tracking.
- **Payments**: Handles transactions and payment statuses.

## Features
- **User Registration & Authentication**
- **Role-Based Access Control (RBAC)**
- **Show & Ticket Management**
- **Booking System with Status Tracking**
- **Payment Integration with MoMo**
- **Asynchronous Processing using Kafka/RabbitMQ**
- **Caching with Redis for Performance Optimization**

## Setup & Installation
### Prerequisites
- Java 17
- MySQL Database
- Redis Server
- Kafka/RabbitMQ

### Steps to Run the Application
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/sanglm2207/ticket-booking-system.git
   cd ticket-booking-system
   ```

2. **Configure the Database**:
    - Update `application.properties` with MySQL credentials.

3. **Build & Run the Application**:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Run Redis & Kafka/RabbitMQ**:
   ```bash
   docker-compose up -d
   ```

5. **Access the API**:
    - Swagger UI: `http://localhost:8080/swagger-ui.html`

## About the Author
For more information about the author **@kaidev99**, you can visit:

- **Official Website**: [https://kaidev99.com](https://kaidev99.com)
- **Facebook**: [https://www.facebook.com/kaidev99](https://www.facebook.com/kaidev99)

#Java #SpringBoot #MySQL #JWT #Security #Kafka #RabbitMQ #Redis #MoMo #TicketBooking #SoftwareDevelopment

