package com.example.happypetshop.util;

import com.example.happypetshop.models.entities.PetEntity;
import com.example.happypetshop.models.entities.UserEntity;
import com.example.happypetshop.models.entities.UserRoleEntity;
import com.example.happypetshop.models.enums.PetSpeciesEnum;
import com.example.happypetshop.models.enums.UserRoleEnum;
import com.example.happypetshop.repositories.PetRepository;
import com.example.happypetshop.repositories.UserRepository;
import com.example.happypetshop.repositories.UserRoleRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class TestDataUtil {

    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private PetRepository petRepository;


    public TestDataUtil(UserRepository userRepository,
                        UserRoleRepository userRoleRepository, PetRepository petRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;

        this.petRepository = petRepository;
    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity().setUserRole(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity().setUserRole(UserRoleEnum.USER);

            userRoleRepository.save(adminRole);
            userRoleRepository.save(userRole);
        }
    }

    public UserEntity createTestAdmin(String email) {

        initRoles();

        var admin = new UserEntity().
                setEmail(email).
                setFirstName("Admin").
                setLastName("Adminov").
                setActive(true).
                setUserRoles(userRoleRepository.findAll());

        return userRepository.save(admin);
    }

    public UserEntity createTestUser(String email) {

        initRoles();

        var user = new UserEntity().
                setEmail(email).
                setFirstName("User").
                setLastName("Userov").
                setActive(true).
                setUserRoles(userRoleRepository.
                        findAll().stream().
                        filter(r -> r.getUserRole() != UserRoleEnum.ADMIN).
                        toList());

        return userRepository.save(user);
    }


    public PetEntity createTestPet(UserEntity owner) {
        var petEntity = new PetEntity();
        petEntity.setAge(1);
        petEntity.setComment("Test comment");
        petEntity.setSpecies(PetSpeciesEnum.CAT);
        petEntity.setBreed("Angorska");
        petEntity.setPictureUrl("https://example.com/picture-url");
        petEntity.setName("Betty");
        petEntity.setPrice(BigDecimal.TEN);
        petEntity.setOwner(owner);

        return petRepository.save(petEntity);
    }

    public void cleanUpDatabase() {
        petRepository.deleteAll();
        userRepository.deleteAll();
        userRoleRepository.deleteAll();

    }
}



