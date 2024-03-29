package com.example.happypetshop.web;


import com.example.happypetshop.models.entities.PetEntity;
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

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountDetailsControllerIT {

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
    void testAccountDetailsPageShown() throws Exception {
        mockMvc.perform(get("/user/details/{id}",testAdmin.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("details"));
    }

    @Test
    @WithUserDetails(value = "admin@example.com",
            userDetailsServiceBeanName = "testUserDataService")
    void testUserEditUsernamePageShown() throws Exception {
        mockMvc.perform(get("/user/edit-username"))
                .andExpect(status().isOk())
                .andExpect(view().name("edit-username"));
    }


    @Test
    @WithUserDetails(value = "admin@example.com",
            userDetailsServiceBeanName = "testUserDataService")
    void testUserAdminDetailsPageShown() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().is4xxClientError());
    }


    @Test
    @WithMockUser(
            username = "admin@example.com",
            roles = {"ADMIN", "USER"}
    )
    void testPostDetails() throws Exception {
        mockMvc.perform(post("/user/edit-username/update/{id}",testAdmin.getId())
                        .param("username","test@test.com")
                        .param("repeatUsername", "test@test.com")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }


    @Test
    @WithMockUser(
            username = "admin@example.com",
            roles = {"ADMIN", "USER"}
    )
    void testPostDetailsMissingData() throws Exception {
        mockMvc.perform(post("/user/edit-username/update/{id}",testAdmin.getId())
                        .param("username","")
                        .param("repeatUsername", "")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/edit-username"));
    }


    @Test
    @WithMockUser(
            username = "admin@example.com",
            roles = {"ADMIN", "USER"}
    )
    void testPostDetailsNotMatchingData() throws Exception {
        mockMvc.perform(post("/user/edit-username/update/{id}",testAdmin.getId())
                        .param("username","test@test.com")
                        .param("repeatUsername", "test@test.com1")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/edit-username"));
    }


    @Test
    @WithMockUser(
            username = "admin@example.com",
            roles = {"ADMIN", "USER"}
    )
    void testPostDetailsOccupiedData() throws Exception {
        mockMvc.perform(post("/user/edit-username/update/{id}",testAdmin.getId())
                        .param("username","admin@example.com")
                        .param("repeatUsername", "admin@example.com1")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/edit-username"));
    }
}
