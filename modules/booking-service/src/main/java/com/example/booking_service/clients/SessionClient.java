package com.example.booking_service.clients;

import com.example.booking_service.dtos.SessionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "session-service")
public interface SessionClient {
    @GetMapping("/api/sessions/{id}")
    SessionDto getSessionById(@PathVariable Long id);
}
