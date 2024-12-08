package com.example.petmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HouseholdStatisticsDTO {
    private long fullHouseholds;
    private long emptyHouseholds;
}
