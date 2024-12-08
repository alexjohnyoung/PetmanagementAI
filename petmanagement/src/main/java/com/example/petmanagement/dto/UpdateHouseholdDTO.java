package com.example.petmanagement.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateHouseholdDTO {
    @NotNull(message = "Number of occupants cannot be null")
    @Min(value = 0, message = "Number of occupants cannot be negative")
    private Integer numberOfOccupants;

    @NotNull(message = "Maximum number of occupants cannot be null")
    @Min(value = 0, message = "Maximum number of occupants cannot be negative")
    private Integer maxNumberOfOccupants;

    private Boolean ownerOccupied;
}
