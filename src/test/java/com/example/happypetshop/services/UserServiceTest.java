package com.example.happypetshop.services;


import com.example.happypetshop.models.entities.UserEntity;
import com.example.happypetshop.models.entities.UserRoleEntity;
import com.example.happypetshop.models.enums.UserRoleEnum;
import com.example.happypetshop.models.mapper.UserMapper;
import com.example.happypetshop.models.user.PetShopUserDetails;
import com.example.happypetshop.repositories.UserRepository;
import com.example.happypetshop.repositories.UserRoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository mockedUserRepo;

    private UserService toTest;

    private PasswordEncoder passwordEncoder;
    private UserMapper userMapper;
    private UserRoleRepository userRoleRepository;
    private UserDetailsService userDetailsService;


    @BeforeEach
    void setUp() {
        toTest = new UserService(
                mockedUserRepo,passwordEncoder,userMapper,userRoleRepository,userDetailsService);
    }


    @Test
    void createUserIfNotExistTest(){



        //arrange
        UserEntity testUserEntity =
                new UserEntity().
                        setEmail("test@test.com").
                        setPassword("asdasdasd").
                        setFirstName("Pesho").
                        setLastName("Peshov").
                        setActive(true).
                        setUserRoles(
                                List.of(new UserRoleEntity().setUserRole(UserRoleEnum.ADMIN),
                                        new UserRoleEntity().setUserRole(UserRoleEnum.USER)
                                )
                        );

        when (mockedUserRepo.findByEmail(testUserEntity.getEmail()))
                .thenReturn(Optional.of(testUserEntity));
        when (mockedUserRepo.count())
                .thenReturn(1L);

        //act
                toTest.createUserIfNotExist(testUserEntity.getEmail());

        UserEntity byEmail = mockedUserRepo.findByEmail(testUserEntity.getEmail()).get();

        //assert
        Assertions.assertEquals(testUserEntity.getEmail(), byEmail.getEmail());
        Assertions.assertEquals(testUserEntity.getPassword(), byEmail.getPassword());
        Assertions.assertEquals(testUserEntity.getFirstName(), byEmail.getFirstName());
        Assertions.assertEquals(testUserEntity.getLastName(), byEmail.getLastName());

        var authorities = byEmail.getUserRoles();

        Assertions.assertEquals(2, byEmail.getUserRoles().size());

        var authIter = authorities.iterator();


        Assertions.assertEquals(UserRoleEnum.ADMIN,
                authIter.next().getUserRole());
        Assertions.assertEquals(UserRoleEnum.USER,
                authIter.next().getUserRole());


       Assertions.assertEquals(1L,mockedUserRepo.count());
    }

}
