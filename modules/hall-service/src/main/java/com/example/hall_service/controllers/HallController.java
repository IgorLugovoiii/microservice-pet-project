package com.example.hall_service.controllers;

import com.example.hall_service.models.Hall;
import com.example.hall_service.services.HallService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/halls")
public class HallController {
    private final HallService hallService;

    @Autowired
    public HallController(HallService hallService) {
        this.hallService = hallService;
    }

    @GetMapping
    public ResponseEntity<List<Hall>> getAllHalls() {
        return new ResponseEntity<>(hallService.getAllHalls(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Hall>> findHallById(@PathVariable Long id) {
        return new ResponseEntity<>(hallService.findHallById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createHall(@Valid @RequestBody Hall hall) {
        return new ResponseEntity<>(hallService.createHall(hall), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateHall(@Valid @RequestBody Hall hall, @PathVariable Long id) {
        return new ResponseEntity<>(hallService.updateHall(hall, id), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHallById(@PathVariable Long id) {
        hallService.deleteHallById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/seats-per-row/{seatsPerRow}")
    public ResponseEntity<List<Hall>> findHallBySeatsPerRow(Integer seatsPerRow) {
        return new ResponseEntity<>(hallService.findHallsBySeatsPerRow(seatsPerRow), HttpStatus.OK);
    }
}
