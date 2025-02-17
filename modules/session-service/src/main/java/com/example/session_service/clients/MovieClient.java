package com.example.session_service.clients;

import com.example.session_service.dtos.MovieDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "movie-service")
public interface MovieClient {
    @GetMapping("/api/movies/{id}")
    MovieDto getMovieById(@PathVariable Long id);
}
