package com.example.petmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePetDTO {
    private String name;
    private String breed;
    private String type;
    private int age;
    private String householdEircode;
}
