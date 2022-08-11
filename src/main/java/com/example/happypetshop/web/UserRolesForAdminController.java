package com.example.happypetshop.web;

import com.example.happypetshop.models.dtos.AdminCommandDTO;
import com.example.happypetshop.models.dtos.UserDetailsAdminDTO;
import com.example.happypetshop.models.entities.UserEntity;
import com.example.happypetshop.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<UserDetailsAdminDTO>> getAllUsers() {
        return ResponseEntity.
                ok(userService.getAll());
    }


    @PostMapping("/admin")
    public ResponseEntity<AdminCommandDTO> receiveAdminCommand(
            @RequestBody AdminCommandDTO adminCommandDTO
    ) {
        this.userService.handleAdminRights(adminCommandDTO);

        return ResponseEntity.
                noContent().
                build();
    }


}
