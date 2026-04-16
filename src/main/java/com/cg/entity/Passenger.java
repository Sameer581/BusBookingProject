package com.cg.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    uniqueConstraints = @UniqueConstraint(columnNames = {"seat_no", "schedule_id"})
)
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passengerName;
    private int passengerAge;
    private int seatNo;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private BusBooking booking;

    // 🔥 Important for seat uniqueness per schedule
    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private RouteSchedule schedule;

    // getters & setters
    
    
}
