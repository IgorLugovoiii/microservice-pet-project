package com.example.hall_service.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "halls")
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name can`t be empty")
    @Size(max = 100, message = "Name is too long")
    private String name;
    @Column(name = "rows", nullable = false)
    @NotNull(message = "Rows can`t be empty")
    @Min(value = 0, message = "Rows can`t be less than 0")
    @Max(value = 10, message = "Rows can`t be more than 10")
    private Integer rows;
    @Column(name = "seats_per_row", nullable = false)
    @NotNull(message = "Seats can`t be empty")
    @Min(value = 0, message = "Rows can`t be less than 0")
    @Max(value = 10, message = "Rows can`t be more than 10")
    private Integer seatsPerRow;
}
