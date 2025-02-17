package com.example.session_service.dtos;

import com.example.session_service.models.Session;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SessionDto {
    private Long id;
    private String movieTitle;
    private String hallName;
    private LocalDateTime startTime;
    private Double ticketPrice;

    public SessionDto(Session session, String movieTitle, String hallName){
        this.id = session.getId();
        this.movieTitle = movieTitle;
        this.hallName = hallName;
        this.startTime = session.getStartTime();
        this.ticketPrice = session.getTicketPrice();
    }
}
