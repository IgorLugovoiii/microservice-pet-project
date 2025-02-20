package com.example.booking_service.controllers;

import com.example.booking_service.dtos.BookingDto;
import com.example.booking_service.models.Booking;
import com.example.booking_service.services.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingService bookingService;
    @Autowired
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @GetMapping
    public ResponseEntity<List<BookingDto>> findAll(){
        return new ResponseEntity<>(bookingService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDto> findBookingById(@PathVariable Long id){
        return new ResponseEntity<>(bookingService.findBookingById(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createBooking(@Valid @RequestBody Booking booking){
        return new ResponseEntity<>(bookingService.createBooking(booking),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBooking(@Valid @RequestBody Booking booking, @PathVariable Long id){
        return new ResponseEntity<>(bookingService.updateBooking(booking, id), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookingById(@PathVariable Long id){
        bookingService.deleteBookingById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
