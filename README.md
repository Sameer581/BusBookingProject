# 🚌 Bus Booking System

A full-stack **Bus Ticket Booking Application** built using **Spring Boot (Backend)** and **Angular (Frontend)**.
This project allows users to search schedules, book seats, and manage bookings with authentication using **JWT**.

---

## 🚀 Features

### 🔐 Authentication

* User Signup & Login
* JWT-based authentication
* Role-based access (`ROLE_USER`)

### 🧭 Bus Scheduling

* Search buses by:

  * Source
  * Destination
  * Date
* View available schedules

### 🎟️ Booking System

* Select seats (A1, B2 format)
* Add passenger details
* Prevent already booked seats
* Store bookings with passengers

### 👤 Customer Management

* Each user linked with a customer profile
* Customer ID used during booking

### 🪑 Seat Layout

* Dynamic seat grid
* Visual indicators:

  * ✅ Available
  * ❌ Booked
  * 🟦 Selected

---

## 🛠️ Tech Stack

### Backend

* Java 17+
* Spring Boot
* Spring Security (JWT)
* Spring Data JPA (Hibernate)
* PostgreSQL / MySQL

### Frontend

* Angular (Standalone Components)
* Bootstrap (UI Styling)

---

## 📂 Project Structure

### Backend (Spring Boot)

```
com.cg
 ├── config        # Security configuration
 ├── controller    # REST APIs
 ├── dto           # Request/Response DTOs
 ├── entity        # JPA entities
 ├── repository    # Database access
 ├── security      # JWT & filters
 └── exception     # Global exception handling
```

### Frontend (Angular)

```
src/app
 ├── login
 ├── signup
 ├── schedule
 ├── booking
 ├── mybookings
 └── searchBus
```

---

## 🔑 Authentication Flow

1. User logs in via `/generateToken`
2. Backend returns JWT + `custId`
3. Token stored in frontend
4. All secured APIs require:

```
Authorization: Bearer <token>
```

---

## ▶️ How to Run

### Backend

```bash
./mvnw spring-boot:run
```

### Frontend

```bash
npm install
ng serve
```

---

## 🔧 Configuration

Update `application.properties`:

```
spring.datasource.url=jdbc:mysql://localhost:3306/busbooking
spring.datasource.username=root
spring.datasource.password=yourpassword
```

---

## 🧪 Testing

* Use **Postman** or **Swagger UI**
* Add JWT token in header:

```
Authorization: Bearer <token>
```

---

## 📌 Future Improvements

* 💳 Payment integration
* 📊 Admin dashboard
* 📍 Live seat availability updates
* 🔔 Email/SMS notifications
* 📱 Mobile app support

---

## ⭐ Acknowledgement

This project was built as part of learning:

* Spring Boot
* Angular
* JWT Authentication
* Full-stack application design

---

## 📜 License

This project is for educational purposes.
