package com.example.petmanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String breed;

    @NotBlank
    private String type;

    @Min(0)
    private int age;

    @ManyToOne(fetch = FetchType.EAGER)  // Eager loading of the household
    @JsonBackReference
    @JoinColumn(name = "household_eircode")
    private Household household;
}
