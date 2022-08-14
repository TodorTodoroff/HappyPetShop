package com.example.happypetshop.services;

import com.example.happypetshop.models.entities.UserEntity;
import com.example.happypetshop.models.entities.UserRoleEntity;
import com.example.happypetshop.models.enums.UserRoleEnum;
import com.example.happypetshop.models.user.PetShopUserDetails;
import com.example.happypetshop.repositories.UserRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PetShopUserDetailsTest {

    @Mock
    private UserRepository mockUserRepo;


    private PetShopUserDetailsService toTest;


    @BeforeEach
    void setUp() {

        toTest = new PetShopUserDetailsService(
                mockUserRepo);

    }


    @Test
    public void testLoadUserByUsername_UserExists() {

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

        when (mockUserRepo.findByEmail(testUserEntity.getEmail()))
                .thenReturn(Optional.of(testUserEntity));

        //act
        PetShopUserDetails userDetails = (PetShopUserDetails)
        toTest.loadUserByUsername(testUserEntity.getEmail());

        //assert
        Assertions.assertEquals(testUserEntity.getEmail(), userDetails.getUsername());
        Assertions.assertEquals(testUserEntity.getPassword(), userDetails.getPassword());
        Assertions.assertEquals(testUserEntity.getFirstName(), userDetails.getFirstName());
        Assertions.assertEquals(testUserEntity.getLastName(), userDetails.getLastName());
        Assertions.assertEquals(testUserEntity.getFirstName() + " " + testUserEntity.getLastName(),
                userDetails.getFullName());

        var authorities = userDetails.getAuthorities();

        Assertions.assertEquals(2, userDetails.getAuthorities().size());

        var authIter = authorities.iterator();


        Assertions.assertEquals("ROLE_" + UserRoleEnum.ADMIN.name(),
                authIter.next().getAuthority());
        Assertions.assertEquals("ROLE_" + UserRoleEnum.USER.name(),
                authIter.next().getAuthority());

    }


    @Test
    public void testLoadUserByUsername_UserDoesNotExists() {

        //arrange: no need to arrange anything

        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername("not-existing@example.com")
        );

    }


}
