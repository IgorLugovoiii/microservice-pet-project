package com.example.booking_service.clients;

import com.example.booking_service.dtos.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "api-gateway")
public interface UserClient {
    @GetMapping("/api/auth/users/{id}")
    UserDto getUserById(@PathVariable Long id);
}
