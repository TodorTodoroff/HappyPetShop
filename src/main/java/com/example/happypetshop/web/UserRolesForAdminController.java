package com.example.happypetshop.web;

import com.example.happypetshop.models.dtos.UserDetailsAdminDTO;
import com.example.happypetshop.models.entities.UserEntity;
import com.example.happypetshop.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/users")
public class UserRolesForAdminController {

    private final UserService userService;

    public UserRolesForAdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<List<UserDetailsAdminDTO>> getAllBooks() {
        return ResponseEntity.
                ok(userService.getAll());
    }
}
