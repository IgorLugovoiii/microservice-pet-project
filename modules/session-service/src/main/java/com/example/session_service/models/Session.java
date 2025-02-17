package com.example.session_service.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Table(name = "session")
@Entity
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @NotNull(message = "Movie id can`t be empty")
    @Column(name = "movie_id", nullable = false)
    private Long movieId;
    @NotNull(message = "Hall id can`t be empty")
    @Column(name = "hall_id", nullable = false)
    private Long hallId;
    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;
    @NotNull(message = "Ticket price can`t be empty")
    @Column(name = "ticket_price", nullable = false)
    private Double ticketPrice;
    @ElementCollection
    private List<String> bookedSeats;
}
