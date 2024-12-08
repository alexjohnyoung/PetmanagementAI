package com.example.petmanagement.resolver;

import com.example.petmanagement.dto.PetDTO;
import com.example.petmanagement.dto.PetDetailsDTO;
import com.example.petmanagement.dto.PetStatisticsDTO;
import com.example.petmanagement.entity.Pet;
import com.example.petmanagement.exception.ResourceNotFoundException;
import com.example.petmanagement.service.PetService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PetQueryResolver {

    private final PetService petService;

    public PetQueryResolver(PetService petService) {
        this.petService = petService;
    }

    @QueryMapping
    public List<PetDetailsDTO> getPetsByType(@Argument String type) {
        List<Pet> pets = petService.findPetsByType(type);
        List<PetDetailsDTO> petDTOs = new ArrayList<>();
        for (Pet pet : pets) {
            petDTOs.add(mapToPetDetailsDTO(pet));
        }
        return petDTOs;
    }

    @QueryMapping
    public PetDetailsDTO getPetById(@Argument Long id) {
        Pet pet = petService.getPetById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found with ID: " + id));
        return mapToPetDetailsDTO(pet);
    }

    @QueryMapping
    public PetStatisticsDTO getPetStatistics() {
        return petService.getPetStatistics(); // Assuming the service already provides a ready-to-use DTO.
    }

    private PetDetailsDTO mapToPetDetailsDTO(Pet pet) {
        return new PetDetailsDTO(
                pet.getId(),
                pet.getName(),
                pet.getBreed(),
                pet.getType(),
                pet.getAge(),
                pet.getHousehold() != null ? pet.getHousehold().getEircode() : null
        );
    }
}

