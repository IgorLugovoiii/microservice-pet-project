package com.example.booking_service.models;

import com.example.booking_service.models.enums.BookingStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "booking")
public class Booking {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "user_id", nullable = false)
    @NotNull(message = "User id can`t be empty")
    private Long userId;
    @Column(name = "session_id", nullable = false)
    @NotNull(message = "Session id can`t be empty")
    private Long sessionId;
    @ElementCollection
    private List<String> seats;
    @Column(name = "booking_time", nullable = false)
    private LocalDateTime bookingTime;
    @Column(name = "booking_status")
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;
}
