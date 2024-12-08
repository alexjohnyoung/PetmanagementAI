package com.example.petmanagement.service;

import com.example.petmanagement.dto.PetDTO;
import com.example.petmanagement.dto.PetDetailsDTO;
import com.example.petmanagement.dto.PetStatisticsDTO;
import com.example.petmanagement.dto.UpdatePetNameDTO;
import com.example.petmanagement.entity.Pet;

import java.util.List;
import java.util.Optional;

public interface PetService {
    List<PetDTO> getAllPets();
    Optional<Pet> getPetById(Long id);
    Pet createPet(Pet pet);
    Pet updatePet(Long id, Pet updatedPet);
    void deletePet(Long id);
    void deletePetsByName(String name);
    List<Pet> findPetsByType(String type);
    List<Pet> findPetsByBreedOrderedByAge(String breed);
    List<PetDTO> getPetNamesAndBreeds();
    PetStatisticsDTO getPetStatistics();
    PetDetailsDTO updatePetName(Long id, UpdatePetNameDTO updatePetNameDTO);
}
