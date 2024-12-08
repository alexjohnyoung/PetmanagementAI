package com.example.petmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseholdWithPetsDTO {
    private String eircode;
    private int numberOfOccupants;
    private int maxNumberOfOccupants;
    private boolean ownerOccupied;
    private List<PetDetailsDTO> pets;
}
