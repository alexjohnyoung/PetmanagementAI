package com.example.petmanagement.resolver;

import com.example.petmanagement.service.PetService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Component;

@Component
public class PetMutationResolver {

    private final PetService petService;

    public PetMutationResolver(PetService petService) {
        this.petService = petService;
    }

    @MutationMapping
    public String deletePetById(@Argument Long id) {
        petService.deletePet(id);
        return "Pet with ID " + id + " deleted successfully.";
    }
}
