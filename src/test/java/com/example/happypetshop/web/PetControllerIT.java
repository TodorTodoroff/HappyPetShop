package com.example.happypetshop.web;


import com.example.happypetshop.models.entities.PetEntity;
import com.example.happypetshop.models.entities.UserEntity;
import com.example.happypetshop.models.enums.PetSpeciesEnum;
import com.example.happypetshop.util.TestDataUtil;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.NestedRuntimeException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.WebApplicationContext;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PetControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestDataUtil testDataUtils;

    private UserEntity testUser, testAdmin;

    private PetEntity testPet;


    @BeforeEach
    void setUp() {
        testUser = testDataUtils.createTestUser("user@example.com");
        testAdmin = testDataUtils.createTestAdmin("admin@example.com");
        testPet = testDataUtils.createTestPet(testUser);

    }

    @AfterEach
    void tearDown() {
        testDataUtils.cleanUpDatabase();
    }

    @Test
    @WithMockUser(
            username = "admin@example.com",
            roles = {"ADMIN", "USER"}
    )
    void testPetRegistrationPageShown() throws Exception {
        mockMvc.perform(get("/pets/register-pet"))
                .andExpect(status().isOk())
                .andExpect(view().name("register-pet"));
    }

    @Test
    @WithMockUser(
            username = "admin@example.com",
            roles = {"ADMIN", "USER"}
    )
    void testPetAllPageShown() throws Exception {
        mockMvc.perform(get("/pets/pets-all"))
                .andExpect(status().isOk())
                .andExpect(view().name("pets-all"));
    }



    @Test
    @WithUserDetails(value = "user@example.com",
            userDetailsServiceBeanName = "testUserDataService")
    void testPetRegistration() throws Exception {
        mockMvc.perform(post("/pets/register-pet")
                        .param("name","Betty")
                        .param("species","CAT")
                        .param("breed", "TESTOVA")
                        .param("age", "1")
                        .param("price", "1001")
                        .param("pictureUrl", "https://test.com/picture.png")
                        .param("comment", "TEST COMMENT")

                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/pets/pets-all"));

    }

    @Test
    @WithUserDetails(value = "user@example.com",
            userDetailsServiceBeanName = "testUserDataService")
    void testPetRegistrationWithInvalidData() throws Exception {
        mockMvc.perform(post("/pets/register-pet")
                        .param("name","")
                        .param("species","CAT")
                        .param("breed", "TESTOVA")
                        .param("age", "1")
                        .param("price", "1001")
                        .param("pictureUrl", "https://test.com/picture.png")
                        .param("comment", "TEST COMMENT")

                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/pets/register-pet"));

    }

    @Test
    @WithUserDetails(value = "user@example.com",
            userDetailsServiceBeanName = "testUserDataService")
    void testPetDetailsWithAuthenticatedUser() throws Exception {
        mockMvc.perform(get("/pets/{id}/details", testPet.getId()).
                        with(csrf())
                )
                 .andExpect(status().isOk())
                .andExpect(view().name("pet-details"));
    }

    @Test
    void testPetDetailsWithAnonymoseUser_Forbidden() throws Exception {
        mockMvc.perform(get("/pets/{id}/details", testPet.getId()).
                        with(csrf())
                )
                .andExpect(status().is3xxRedirection());
    }

//    @Test
//    @WithUserDetails(value = "user@example.com",
//            userDetailsServiceBeanName = "testUserDataService")
//    @ExceptionHandler(RuntimeException.class)
//    void testPetDetailsThrow() throws Exception {
//        mockMvc.perform(get("/pets/{id}/details", 2).
//                        with(csrf())
//                )
//                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RuntimeException));
//    }

}
