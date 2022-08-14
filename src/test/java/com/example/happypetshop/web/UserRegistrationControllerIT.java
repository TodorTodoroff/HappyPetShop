package com.example.happypetshop.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRegistrationControllerIT {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void testRegistrationPageShown() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }


//    @Test
//    void testUserRegistration() throws Exception {
//        mockMvc.perform(post("/register")
//                .param("email","test@test.com")
//                .param("firstName", "Pesho")
//                .param("lastName", "Peshov")
//                .param("password", "asdasdasd")
//                .param("confirmPassword", "asdasdasd")
//                                .with(csrf())
//                )
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/"));
//    }

    @Test
    void testUserRegistrationInvalidData() throws Exception {
        mockMvc.perform(post("/register")
                        .param("email","")
                        .param("firstName", "")
                        .param("lastName", "Peshov")
                        .param("password", "asdasdasd")
                        .param("confirmPassword", "asdasdasd")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/register"));


    }

}
