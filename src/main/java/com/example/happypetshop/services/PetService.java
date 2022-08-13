package com.example.happypetshop.services;


import com.example.happypetshop.models.dtos.PetDetailDTO;
import com.example.happypetshop.models.dtos.PetRegisterDTO;
import com.example.happypetshop.models.entities.PetEntity;
import com.example.happypetshop.models.entities.UserEntity;
import com.example.happypetshop.models.mapper.PetMapper;
import com.example.happypetshop.models.user.PetShopUserDetails;
import com.example.happypetshop.repositories.PetRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final PetMapper petMapper;
    private final UserService userService;

    public PetService(PetRepository petRepository, PetMapper petMapper, UserService userService) {
        this.petRepository = petRepository;
        this.petMapper = petMapper;
        this.userService = userService;
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

    public void save(PetRegisterDTO petModel, PetShopUserDetails userDetails) {

        PetEntity pet = this.petMapper.petRegisterDTOtoPetEntity(petModel);

        UserEntity user = this.userService.getUserByEmail(userDetails.getUsername());

        pet.setOwner(user);

        this.petRepository.save(pet);
    }
}
