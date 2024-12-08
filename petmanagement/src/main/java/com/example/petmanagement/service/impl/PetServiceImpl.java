package com.example.petmanagement.service.impl;

import com.example.petmanagement.dto.PetDTO;
import com.example.petmanagement.dto.PetDetailsDTO;
import com.example.petmanagement.dto.PetStatisticsDTO;
import com.example.petmanagement.dto.UpdatePetNameDTO;
import com.example.petmanagement.entity.Pet;
import com.example.petmanagement.exception.ResourceNotFoundException;
import com.example.petmanagement.repository.PetRepository;
import com.example.petmanagement.service.PetService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public List<PetDTO> getAllPets() {
        return petRepository.findAll().stream()
                .map(pet -> new PetDTO(pet.getName(), pet.getBreed()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Pet> getPetById(Long id) {
        return petRepository.findById(id);
    }

    @Override
    public Pet createPet(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public Pet updatePet(Long id, Pet updatedPet) {
        return petRepository.findById(id).map(existingPet -> {
            existingPet.setName(updatedPet.getName());
            existingPet.setAge(updatedPet.getAge());
            existingPet.setBreed(updatedPet.getBreed());
            return petRepository.save(existingPet);
        }).orElseThrow(() -> new IllegalArgumentException("Pet not found with id: " + id));
    }

    @Override
    public PetDetailsDTO updatePetName(Long id, UpdatePetNameDTO updatePetNameDTO) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found with ID: " + id));

        // Update the pet's name
        pet.setName(updatePetNameDTO.getName());
        pet = petRepository.save(pet);

        // Map Pet to PetResponseDTO
        PetDetailsDTO petResponseDTO = new PetDetailsDTO();
        petResponseDTO.setName(pet.getName());
        petResponseDTO.setBreed(pet.getBreed());
        petResponseDTO.setType(pet.getType());
        petResponseDTO.setAge(pet.getAge());
        petResponseDTO.setHouseholdEircode(pet.getHousehold().getEircode());

        return petResponseDTO;
    }

    @Override
    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }

    @Override
    public void deletePetsByName(String name) {
        petRepository.deleteByNameIgnoreCase(name);
    }

    @Override
    public List<Pet> findPetsByType(String type) {
        return petRepository.findByTypeIgnoreCase(type);
    }

    @Override
    public List<Pet> findPetsByBreedOrderedByAge(String breed) {
        return petRepository.findByBreedOrderByAge(breed);
    }

    @Override
    public List<PetDTO> getPetNamesAndBreeds() {
        return petRepository.findAll().stream()
                .map(pet -> new PetDTO(pet.getName(), pet.getBreed()))
                .collect(Collectors.toList());
    }

    @Override
    public PetStatisticsDTO getPetStatistics() {
        List<Pet> pets = petRepository.findAll();
        double averageAge = pets.stream().mapToInt(Pet::getAge).average().orElse(0);
        int oldestAge = pets.stream().mapToInt(Pet::getAge).max().orElse(0);

        System.out.println("Average Age: " + averageAge);
        System.out.println("Oldest Age: " + oldestAge);

        return new PetStatisticsDTO(averageAge, oldestAge);
    }
}
