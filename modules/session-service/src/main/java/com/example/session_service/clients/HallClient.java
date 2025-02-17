package com.example.session_service.clients;

import com.example.session_service.dtos.HallDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "hall-service")
public interface HallClient {
    @GetMapping("/api/halls/{id}")
    HallDto getHallById(@PathVariable Long id);
}
