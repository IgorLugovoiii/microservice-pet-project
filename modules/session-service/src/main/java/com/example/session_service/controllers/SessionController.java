package com.example.session_service.controllers;

import com.example.session_service.dtos.SessionDto;
import com.example.session_service.models.Session;
import com.example.session_service.repositories.SessionRepository;
import com.example.session_service.services.SessionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {
    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping
    public ResponseEntity<Page<SessionDto>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return new ResponseEntity<>(sessionService.findAll(pageable), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<SessionDto>> findById(@PathVariable Long id){
        return new ResponseEntity<>(sessionService.findSessionById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> createSession(@Valid @RequestBody Session session){
        return new ResponseEntity<>(sessionService.createSession(session),HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSession(@Valid @RequestBody Session session, @PathVariable Long id){
        return new ResponseEntity<>(sessionService.updateSession(session,id),HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        sessionService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
