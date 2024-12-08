package com.example.petmanagement.controller;

import com.example.petmanagement.dto.HouseholdStatisticsDTO;
import com.example.petmanagement.dto.UpdateHouseholdDTO;
import com.example.petmanagement.entity.Household;
import com.example.petmanagement.service.HouseholdService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/households")
public class HouseholdController {

    private final HouseholdService householdService;

    public HouseholdController(HouseholdService householdService) {
        this.householdService = householdService;
    }

    @GetMapping
    public List<Household> getAllHouseholds() {
        return householdService.getAllHouseholds();
    }

    @PostMapping
    public Household createHousehold(@RequestBody Household household) {
        return householdService.createHousehold(household);
    }

    @GetMapping("/{eircode}")
    public ResponseEntity<Household> getHousehold(@PathVariable String eircode) {
        return householdService.getHouseholdByEircode(eircode)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{eircode}")
    public ResponseEntity<Household> updateHousehold(@PathVariable String eircode, @RequestBody UpdateHouseholdDTO updateHouseholdDTO) {
        Household updatedHousehold = householdService.updateHousehold(eircode, updateHouseholdDTO);
        return ResponseEntity.ok(updatedHousehold);
    }

    @DeleteMapping("/{eircode}")
    public ResponseEntity<Void> deleteHousehold(@PathVariable String eircode) {
        householdService.deleteHousehold(eircode);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/no-pets")
    public List<Household> findHouseholdsWithNoPets() {
        return householdService.findHouseholdsWithNoPets();
    }

    @GetMapping("/owner-occupied")
    public List<Household> findOwnerOccupiedHouseholds() {
        return householdService.findOwnerOccupiedHouseholds();
    }

    @GetMapping("/statistics")
    public HouseholdStatisticsDTO getHouseholdStatistics() {
        return householdService.getHouseholdStatistics();
    }
}
