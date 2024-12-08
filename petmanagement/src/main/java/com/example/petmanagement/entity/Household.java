package com.example.petmanagement.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Household {

    @Id
    @NotNull
    @Size(min = 7, max = 7, message = "Eircode must be exactly 7 characters")
    private String eircode;

    @Min(1)
    private int numberOfOccupants;

    @Min(1)
    private int maxNumberOfOccupants;

    private boolean ownerOccupied;

    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Pet> pets = new ArrayList<>();
}
