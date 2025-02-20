package com.example.booking_service.dtos;

import com.example.booking_service.models.Booking;
import com.example.booking_service.models.enums.BookingStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BookingDto {
    private Long id;
    private String username;
    private String movieTitle;
    private List<String> seats;
    private LocalDateTime bookingTime;
    private BookingStatus bookingStatus;

    public BookingDto(Booking booking, UserDto userDto, MovieDto movieDto){
        this.id = booking.getId();
        this.username = userDto.getUsername();
        this.movieTitle = movieDto.getTitle();
        this.seats = booking.getSeats();
        this.bookingTime = booking.getBookingTime();
        this.bookingStatus = getBookingStatus();
    }
}
