package com.example.petmanagement.service;

import com.example.petmanagement.dto.HouseholdStatisticsDTO;
import com.example.petmanagement.dto.UpdateHouseholdDTO;
import com.example.petmanagement.entity.Household;

import java.util.List;
import java.util.Optional;

public interface HouseholdService {
    List<Household> getAllHouseholds();
    Optional<Household> getHouseholdByEircode(String eircode);
    Household createHousehold(Household household);
    void deleteHousehold(String eircode);
    Household updateHousehold(String eircode, UpdateHouseholdDTO updateHouseholdDTO);

    // Find households with no pets
    List<Household> findHouseholdsWithNoPets();

    // Find households that are owner-occupied
    List<Household> findOwnerOccupiedHouseholds();

    // Get household statistics
    HouseholdStatisticsDTO getHouseholdStatistics();
}
