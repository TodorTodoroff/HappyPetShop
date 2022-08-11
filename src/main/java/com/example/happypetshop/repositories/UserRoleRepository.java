package com.example.happypetshop.repositories;

import com.example.happypetshop.models.entities.UserRoleEntity;
import com.example.happypetshop.models.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    UserRoleEntity findByUserRole(UserRoleEnum admin);
}
