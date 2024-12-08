package com.example.petmanagement.repository;

import com.example.petmanagement.entity.Household;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HouseholdRepository extends JpaRepository<Household, String> {
    List<Household> findByPetsIsEmpty();

    List<Household> findByOwnerOccupiedTrue();
}
