package com.example.petmanagement.service.impl;

import com.example.petmanagement.dto.HouseholdStatisticsDTO;
import com.example.petmanagement.dto.UpdateHouseholdDTO;
import com.example.petmanagement.entity.Household;
import com.example.petmanagement.repository.HouseholdRepository;
import com.example.petmanagement.service.HouseholdService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HouseholdServiceImpl implements HouseholdService {

    private final HouseholdRepository householdRepository;

    public HouseholdServiceImpl(HouseholdRepository householdRepository) {
        this.householdRepository = householdRepository;
    }

    @Override
    public List<Household> getAllHouseholds() {
        return householdRepository.findAll();
    }

    @Override
    public Optional<Household> getHouseholdByEircode(String eircode) {
        return householdRepository.findById(eircode);
    }

    @Override
    public Household createHousehold(Household household) {
        return householdRepository.save(household);
    }

    public Household updateHousehold(String eircode, UpdateHouseholdDTO updateHouseholdDTO) {
        Household household = householdRepository.findById(eircode).orElseThrow(() -> new RuntimeException("Household not found"));

        if (updateHouseholdDTO.getNumberOfOccupants() <= updateHouseholdDTO.getMaxNumberOfOccupants()) {
            household.setNumberOfOccupants(updateHouseholdDTO.getNumberOfOccupants());
            household.setMaxNumberOfOccupants(updateHouseholdDTO.getMaxNumberOfOccupants());
            household.setOwnerOccupied(updateHouseholdDTO.getOwnerOccupied());
        } else {
            throw new RuntimeException("Number of occupants cannot exceed max occupants.");
        }

        return householdRepository.save(household);
    }

    public HouseholdStatisticsDTO getHouseholdStatistics() {
        long totalHouseholds = householdRepository.count();
        long emptyHouseholds = householdRepository.findByPetsIsEmpty().size();
        long fullHouseholds = totalHouseholds - emptyHouseholds;

        return new HouseholdStatisticsDTO(fullHouseholds, emptyHouseholds);
    }

    public List<Household> findHouseholdsWithNoPets() {
        return householdRepository.findByPetsIsEmpty();
    }

    public List<Household> findOwnerOccupiedHouseholds() {
        return householdRepository.findByOwnerOccupiedTrue();
    }

    @Override
    public void deleteHousehold(String eircode) {
        householdRepository.deleteById(eircode);
    }
}
