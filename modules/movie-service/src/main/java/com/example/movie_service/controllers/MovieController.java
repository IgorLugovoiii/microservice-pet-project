package com.example.movie_service.controllers;

import com.example.movie_service.models.Movie;
import com.example.movie_service.services.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;
    @Autowired
    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies(){
        return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Movie>> findById(@PathVariable Long id){
        return new ResponseEntity<>(movieService.findMovieById(id),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> createMovie(@Valid @RequestBody Movie movie){
        return new ResponseEntity<>(movieService.createMovie(movie),HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovie(@Valid @RequestBody Movie movie, @PathVariable Long id){
        return new ResponseEntity<>(movieService.updateMovie(movie, id), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        movieService.deleteMovieById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/movies-by-genre/{genre}")
    public ResponseEntity<List<Movie>> findMoviesByGenre(@PathVariable String genre){
        return new ResponseEntity<>(movieService.findMoviesByGenre(genre),HttpStatus.OK);
    }
}
