package com.example.happypetshop.repositories;

import com.example.happypetshop.models.entities.UserEntity;
import com.example.happypetshop.services.PetShopUserDetailsService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByEmail(String username);
}
