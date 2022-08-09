package com.example.happypetshop.services;


import com.example.happypetshop.models.dtos.PetDetailDTO;
import com.example.happypetshop.models.mapper.PetMapper;
import com.example.happypetshop.repositories.PetRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PetService {

    private final PetRepository petRepository;

    private final PetMapper petMapper;

    public PetService(PetRepository petRepository, PetMapper petMapper) {
        this.petRepository = petRepository;
        this.petMapper = petMapper;
    }


    public Page<PetDetailDTO> getAllPets(Pageable pageable) {
        return this.petRepository.
                findAll(pageable)
                .map(this.petMapper::petEntityToPetDetailDTO);
    }

    public Optional<PetDetailDTO> getPetById(Long id) {
        return this.petRepository.
                findById(id).
                map(this.petMapper::petEntityToPetDetailDTO);
    }
}
