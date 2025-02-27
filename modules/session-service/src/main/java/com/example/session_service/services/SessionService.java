package com.example.session_service.services;

import com.example.session_service.clients.HallClient;
import com.example.session_service.clients.MovieClient;
import com.example.session_service.dtos.HallDto;
import com.example.session_service.dtos.MovieDto;
import com.example.session_service.dtos.SessionDto;
import com.example.session_service.models.Session;
import com.example.session_service.repositories.SessionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SessionService {
    private final SessionRepository sessionRepository;
    private final MovieClient movieClient;
    private final HallClient hallClient;

    @Autowired
    public SessionService(SessionRepository sessionRepository, MovieClient movieClient,
                          HallClient hallClient) {
        this.sessionRepository = sessionRepository;
        this.movieClient = movieClient;
        this.hallClient = hallClient;
    }

    private SessionDto convertToSessionDto(Session session) {
        MovieDto movieDto = movieClient.getMovieById(session.getMovieId());
        HallDto hallDto = hallClient.getHallById(session.getHallId());

        return new SessionDto(session, movieDto.getTitle(), hallDto.getName());
    }

    @Transactional(readOnly = true)
    public Page<SessionDto> findAll(Pageable pageable) {
        return sessionRepository.findAll(pageable).map(this::convertToSessionDto);
    }

    @Transactional(readOnly = true)
    public Optional<SessionDto> findSessionById(Long id) {
        return sessionRepository.findById(id).map(this::convertToSessionDto);
    }

    @Transactional
    public SessionDto createSession(Session session) {
        return convertToSessionDto(sessionRepository.save(session));
    }

    @Transactional
    public SessionDto updateSession(Session updatedSession, Long id) {
        Session oldSession = sessionRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        oldSession.setHallId(updatedSession.getHallId());
        oldSession.setMovieId(updatedSession.getMovieId());
        oldSession.setBookedSeats(updatedSession.getBookedSeats());
        oldSession.setStartTime(updatedSession.getStartTime());
        oldSession.setTicketPrice(updatedSession.getTicketPrice());

        return convertToSessionDto(sessionRepository.save(oldSession));
    }

    @Transactional
    public void deleteById(Long id) {
        sessionRepository.deleteById(id);
    }
    @KafkaListener(topics = "movie-removed", groupId = "session-group")
    @Transactional
    public void handleMovieRemoved(String movieId) {
        Long id = Long.parseLong(movieId);
        sessionRepository.deleteByMovieId(id);
        System.out.println("Deleted all sessions with ID: " + id);
    }
}
