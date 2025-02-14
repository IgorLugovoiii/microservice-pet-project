package com.example.hall_service.repostitories;

import com.example.hall_service.models.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {
    List<Hall> findHallsBySeatsPerRow(Integer seatsPerRow);
}
