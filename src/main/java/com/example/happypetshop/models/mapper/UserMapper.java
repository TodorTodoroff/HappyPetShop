package com.example.happypetshop.models.mapper;

import com.example.happypetshop.models.dtos.UserDetailsAdminDTO;
import com.example.happypetshop.models.dtos.UserRegisterDTO;
import com.example.happypetshop.models.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "active", constant = "true")
    UserEntity userDtoToUserEntity(UserRegisterDTO registerDTO);

    UserDetailsAdminDTO userDetailAdminDTOtoUserEntity(UserEntity user);
}
