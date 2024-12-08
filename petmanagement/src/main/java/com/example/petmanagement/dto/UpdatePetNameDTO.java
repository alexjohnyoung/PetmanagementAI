package com.example.petmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePetNameDTO {

    @NotBlank(message = "Pet name cannot be blank.")
    @Size(min = 1, max = 50, message = "Pet name must be between 1 and 50 characters.")
    private String name;
}
