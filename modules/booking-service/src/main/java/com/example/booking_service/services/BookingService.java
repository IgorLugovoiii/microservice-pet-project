package com.example.booking_service.services;

import com.example.booking_service.clients.MovieClient;
import com.example.booking_service.clients.SessionClient;
import com.example.booking_service.clients.UserClient;
import com.example.booking_service.dtos.BookingDto;
import com.example.booking_service.dtos.MovieDto;
import com.example.booking_service.dtos.SessionDto;
import com.example.booking_service.dtos.UserDto;
import com.example.booking_service.models.Booking;
import com.example.booking_service.repositories.BookingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final UserClient userClient;
    private final MovieClient movieClient;
    private final SessionClient sessionClient;

    @Autowired
    public BookingService(BookingRepository bookingRepository, UserClient userClient,
                          MovieClient movieClient, SessionClient sessionClient) {
        this.bookingRepository = bookingRepository;
        this.userClient = userClient;
        this.movieClient = movieClient;
        this.sessionClient = sessionClient;
    }

    public BookingDto convertToBookingDto(Booking booking) {
        UserDto userDto = userClient.getUserById(booking.getUserId());
        SessionDto sessionDto = sessionClient.getSessionById(booking.getSessionId());
        MovieDto movieDto = movieClient.getMovieById(sessionDto.getMovieId());
        return new BookingDto(booking, userDto, movieDto);
    }

    @Transactional(readOnly = true)
    public List<BookingDto> findAll() {
        return bookingRepository.findAll().stream().map(this::convertToBookingDto).toList();
    }

    @Transactional(readOnly = true)
    public BookingDto findBookingById(Long id) {
        return convertToBookingDto(bookingRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new));
    }

    @Transactional
    public BookingDto createBooking(Booking booking){
        return convertToBookingDto(bookingRepository.save(booking));
    }

    @Transactional
    public BookingDto updateBooking(Booking updatedBooking, Long id){
        Booking oldBooking = bookingRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        oldBooking.setUserId(updatedBooking.getUserId());
        oldBooking.setBookingTime(updatedBooking.getBookingTime());
        oldBooking.setBookingStatus(updatedBooking.getBookingStatus());
        oldBooking.setSessionId(updatedBooking.getSessionId());
        oldBooking.setSeats(updatedBooking.getSeats());

        return convertToBookingDto(bookingRepository.save(oldBooking));
    }

    @Transactional
    public void deleteBookingById(Long id){
        bookingRepository.deleteById(id);
    }
}
