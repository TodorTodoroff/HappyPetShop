package com.example.happypetshop.web;


import com.example.happypetshop.models.entities.UserEntity;
import com.example.happypetshop.util.TestDataUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentControllerIT {

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
    void testCommentsPageShown() throws Exception {
        mockMvc.perform(get("/comments"))
                .andExpect(status().isOk())
                .andExpect(view().name("comments"));
    }


    @Test
    @WithUserDetails(value = "user@example.com",
            userDetailsServiceBeanName = "testUserDataService")
    void testSubmitCommentRedirect() throws Exception {
        mockMvc.perform(post("/comments/submit/{id}",testUser.getId())
                        .param("comment", "TEST COMMENT ")

                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/comments"));
    }


    @Test
    @WithUserDetails(value = "user@example.com",
            userDetailsServiceBeanName = "testUserDataService")
    void testSubmitCommentWithNoInput() throws Exception {
        mockMvc.perform(post("/comments/submit/{id}",testUser.getId())
                        .param("comment", "")

                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/comments"));
    }

}
