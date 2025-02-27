package com.example.movie_service.services;

import com.example.movie_service.models.Movie;
import com.example.movie_service.repositories.MovieRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public MovieService(MovieRepository movieRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.movieRepository = movieRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Transactional(readOnly = true)
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Movie> findMovieById(Long id) {
        return movieRepository.findById(id);
    }

    @Transactional
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Transactional
    public Movie updateMovie(Movie updatedMovie, Long id) {
        Movie oldMovie = movieRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        oldMovie.setTitle(updatedMovie.getTitle());
        oldMovie.setDescription(updatedMovie.getDescription());
        oldMovie.setGenre(updatedMovie.getGenre());
        oldMovie.setDuration(updatedMovie.getDuration());
        oldMovie.setAgeRating(updatedMovie.getAgeRating());

        return movieRepository.save(oldMovie);
    }

    @Transactional
    public void deleteMovieById(Long id) {
        movieRepository.deleteById(id);
        kafkaTemplate.send("movie-removed", String.valueOf(id));
    }

    @Transactional(readOnly = true)
    public List<Movie> findMoviesByGenre(String genre) {
        return movieRepository.findMoviesByGenre(genre);
    }
}
