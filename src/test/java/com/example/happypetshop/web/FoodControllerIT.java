package com.example.happypetshop.web;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc

public class FoodControllerIT {


    @Autowired
    private MockMvc mockMvc;

//    @Test
//    @WithMockUser(
//            username = "admin@example.com",
//            roles = {"ADMIN", "USER"}
//    )
//    void testPetRegistrationPageShown() throws Exception {
//        mockMvc.perform(get("/foods"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("foods"));
//    }
}
