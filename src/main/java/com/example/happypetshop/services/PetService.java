package com.example.happypetshop.services;


import com.example.happypetshop.repositories.PetRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    private final PetRepository petRepository;


    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }


    public Page<PetDetailDTO> getAllPets(Pageable pageable) {
        return this.petRepository.
                findAll(pageable)
                .map(this.petMapper.);
//TODO: implement the mapper

    }
}
