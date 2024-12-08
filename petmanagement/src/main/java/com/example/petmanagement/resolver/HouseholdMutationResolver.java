package com.example.petmanagement.resolver;

import com.example.petmanagement.dto.HouseholdWithPetsDTO;
import com.example.petmanagement.dto.PetDTO;
import com.example.petmanagement.dto.PetDetailsDTO;
import com.example.petmanagement.entity.Household;
import com.example.petmanagement.entity.Pet;
import com.example.petmanagement.service.HouseholdService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HouseholdMutationResolver {

    private final HouseholdService householdService;

    public HouseholdMutationResolver(HouseholdService householdService) {
        this.householdService = householdService;
    }

    @MutationMapping
    public HouseholdWithPetsDTO createHousehold(
            @Argument String eircode,
            @Argument int numberOfOccupants,
            @Argument int maxNumberOfOccupants,
            @Argument boolean ownerOccupied) {

        // Create the household entity
        Household household = new Household();
        household.setEircode(eircode);
        household.setNumberOfOccupants(numberOfOccupants);
        household.setMaxNumberOfOccupants(maxNumberOfOccupants);
        household.setOwnerOccupied(ownerOccupied);

        // Save the household
        Household savedHousehold = householdService.createHousehold(household);

        // Map to DTO
        return mapToHouseholdWithPetsDTO(savedHousehold);
    }

    private HouseholdWithPetsDTO mapToHouseholdWithPetsDTO(Household household) {
        List<PetDetailsDTO> petDTOs = new ArrayList<>();
        if (household.getPets() != null) {
            for (Pet pet : household.getPets()) {
                petDTOs.add(mapToPetDetailsDTO(pet));
            }
        }

        return new HouseholdWithPetsDTO(
                household.getEircode(),
                household.getNumberOfOccupants(),
                household.getMaxNumberOfOccupants(),
                household.isOwnerOccupied(),
                petDTOs
        );
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