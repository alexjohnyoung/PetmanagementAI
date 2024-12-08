package com.example.petmanagement.controller;

import com.example.petmanagement.dto.PetDTO;
import com.example.petmanagement.dto.PetStatisticsDTO;
import com.example.petmanagement.entity.Pet;
import com.example.petmanagement.service.PetService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
@Validated
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public List<PetDTO> getAllPets() {
        return petService.getAllPets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable Long id) {
        return petService.getPetById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pet createPet(@RequestBody @Valid Pet pet) {
        return petService.createPet(pet);
    }

    @PutMapping("/{id}")
    public Pet updatePet(@PathVariable Long id, @RequestBody @Valid Pet pet) {
        return petService.updatePet(id, pet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        petService.deletePet(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/name/{name}")
    public ResponseEntity<Void> deletePetsByName(@PathVariable String name) {
        petService.deletePetsByName(name);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/type/{type}")
    public List<Pet> findPetsByType(@PathVariable String type) {
        return petService.findPetsByType(type);
    }

    @GetMapping("/breed/{breed}")
    public List<Pet> findPetsByBreedOrderedByAge(@PathVariable String breed) {
        return petService.findPetsByBreedOrderedByAge(breed);
    }

    @GetMapping("/names-and-breeds")
    public List<PetDTO> getPetNamesAndBreeds() {
        return petService.getPetNamesAndBreeds();
    }

    @GetMapping("/statistics")
    public PetStatisticsDTO getPetStatistics() {
        return petService.getPetStatistics();
    }
}
