package com.example.petmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetDetailsDTO {
    private Long id;
    private String name;
    private String breed;
    private String type;
    private int age;
    private String householdEircode;

}
