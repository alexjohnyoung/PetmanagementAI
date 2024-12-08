package com.example.petmanagement.dto;

import com.example.petmanagement.entity.Pet;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetDTO {
    private String name;
    private String breed;
}
