package com.example.petmanagement.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdatePetDTO {
    @Size(max = 50, message = "Name must be at most 50 characters")
    private String name;

    @Size(max = 50, message = "Breed must be at most 50 characters")
    private String breed;

    @Size(max = 20, message = "Type must be at most 20 characters")
    private String type;

    @Min(value = 0, message = "Pet age must be non-negative")
    private Integer age;
}
