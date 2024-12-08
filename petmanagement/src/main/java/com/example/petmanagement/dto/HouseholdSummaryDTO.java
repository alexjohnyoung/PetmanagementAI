package com.example.petmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseholdSummaryDTO {
    private String eircode;
    private int numberOfOccupants;
    private boolean ownerOccupied;
}
