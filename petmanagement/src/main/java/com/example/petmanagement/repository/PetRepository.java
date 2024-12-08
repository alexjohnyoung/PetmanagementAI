package com.example.petmanagement.repository;

import com.example.petmanagement.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    void deleteByNameIgnoreCase(String name);

    List<Pet> findByTypeIgnoreCase(String type);

    List<Pet> findByBreedOrderByAge(String breed);
}
