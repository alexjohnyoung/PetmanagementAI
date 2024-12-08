package com.example.petmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateHouseholdDTO {
    private String eircode;
    private int numberOfOccupants;
    private int maxNumberOfOccupants;
    private boolean ownerOccupied;
}
