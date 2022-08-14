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
    @WithMockUser(
            username = "admin@example.com",
            roles = {"ADMIN", "USER"}
    )
    void testAccountDetailsPageShown() throws Exception {
        mockMvc.perform(get("/user/details"))
                .andExpect(status().isOk())
                .andExpect(view().name("details"));
    }

    @Test
    @WithMockUser(
            username = "admin@example.com",
            roles = {"ADMIN", "USER"}
    )
    void testUserEditUsernamePageShown() throws Exception {
        mockMvc.perform(get("/user/edit-username"))
                .andExpect(status().isOk())
                .andExpect(view().name("edit-username"));
    }


    @Test
    @WithMockUser(
            username = "admin@example.com",
            roles = {"ADMIN", "USER"}
    )
    void testUserAdminDetailsPageShown() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(view().name("user-admin-details"));
    }


    @Test
    @WithMockUser(
            username = "admin@example.com",
            roles = {"ADMIN", "USER"}
    )
    void testPostDetails() throws Exception {
        mockMvc.perform(post("/user/edit-username/update")
                        .param("username","test@test.com")
                        .param("repeatUsername", "test@test.com")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/details"));
    }


    @Test
    @WithMockUser(
            username = "admin@example.com",
            roles = {"ADMIN", "USER"}
    )
    void testPostDetailsMissingData() throws Exception {
        mockMvc.perform(post("/user/edit-username/update")
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
        mockMvc.perform(post("/user/edit-username/update")
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
        mockMvc.perform(post("/user/edit-username/update")
                        .param("username","admin@example.com")
                        .param("repeatUsername", "admin@example.com1")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/edit-username"));
    }
}
