package com.example.hall_service.services;

import com.example.hall_service.models.Hall;
import com.example.hall_service.repostitories.HallRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HallService {
    private final HallRepository hallRepository;

    @Autowired
    public HallService(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    @Transactional(readOnly = true)
    public List<Hall> getAllHalls() {
        return hallRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Hall> findHallById(Long id) {
        return hallRepository.findById(id);
    }

    @Transactional
    public Hall createHall(Hall hall) {
        return hallRepository.save(hall);
    }

    @Transactional
    public Hall updateHall(Hall updatedHall, Long id) {
        Hall oldHall = hallRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        oldHall.setName(updatedHall.getName());
        oldHall.setRows(updatedHall.getRows());
        oldHall.setSeatsPerRow(updatedHall.getSeatsPerRow());

        return hallRepository.save(oldHall);
    }

    @Transactional
    public void deleteHallById(Long id) {
        hallRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Hall> findHallsBySeatsPerRow(Integer seatsPerRow) {
        return hallRepository.findHallsBySeatsPerRow(seatsPerRow);
    }
}
