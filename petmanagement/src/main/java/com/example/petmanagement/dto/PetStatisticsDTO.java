package com.example.petmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PetStatisticsDTO {
    private double averageAge;
    private int oldestAge;
}
