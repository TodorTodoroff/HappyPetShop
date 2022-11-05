package com.example.happypetshop.web;


import com.example.happypetshop.models.entities.UserEntity;
import com.example.happypetshop.util.TestDataUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc

public class FoodControllerIT {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestDataUtil testDataUtils;

    private UserEntity testUser, testAdmin;



    @BeforeEach
    void setUp() {
        testUser = testDataUtils.createTestUser("user@example.com");
        testAdmin = testDataUtils.createTestAdmin("admin@example.com");
    }
    @AfterEach
    void tearDown() {
        testDataUtils.cleanUpDatabase();
    }

    @Test
    @WithUserDetails(value = "user@example.com",
            userDetailsServiceBeanName = "testUserDataService")
    void testPetRegistrationPageShown() throws Exception {
        mockMvc.perform(get("/foods"))
                .andExpect(status().isOk())
                .andExpect(view().name("foods"));
    }
}
