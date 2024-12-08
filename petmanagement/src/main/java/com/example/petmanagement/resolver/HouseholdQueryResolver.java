package com.example.petmanagement.resolver;

import com.example.petmanagement.dto.HouseholdWithPetsDTO;
import com.example.petmanagement.dto.PetDTO;
import com.example.petmanagement.dto.PetDetailsDTO;
import com.example.petmanagement.entity.Household;
import com.example.petmanagement.entity.Pet;
import com.example.petmanagement.exception.ResourceNotFoundException;
import com.example.petmanagement.service.HouseholdService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HouseholdQueryResolver {

    private final HouseholdService householdService;

    public HouseholdQueryResolver(HouseholdService householdService) {
        this.householdService = householdService;
    }

    @QueryMapping
    public List<HouseholdWithPetsDTO> getAllHouseholds() {
        List<Household> households = householdService.getAllHouseholds();
        List<HouseholdWithPetsDTO> householdDTOs = new ArrayList<>();
        for (Household household : households) {
            householdDTOs.add(mapToHouseholdWithPetsDTO(household));
        }
        return householdDTOs;
    }

    @QueryMapping
    public HouseholdWithPetsDTO getHouseholdByEircode(@Argument String eircode) {
        Household household = householdService.getHouseholdByEircode(eircode)
                .orElseThrow(() -> new ResourceNotFoundException("Household not found with Eircode: " + eircode));
        return mapToHouseholdWithPetsDTO(household);
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
                pet.getHousehold().getEircode()
        );
    }
}
