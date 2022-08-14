package com.example.happypetshop.services;


import com.example.happypetshop.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository mockedUserRepo;

    private UserService toTest;

    @BeforeEach
    void setUp(){

    }

}
