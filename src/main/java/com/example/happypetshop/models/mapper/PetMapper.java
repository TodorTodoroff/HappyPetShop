package com.example.happypetshop.models.mapper;

import com.example.happypetshop.models.dtos.PetDetailDTO;
import com.example.happypetshop.models.dtos.PetRegisterDTO;
import com.example.happypetshop.models.entities.PetEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PetMapper {


    @Mapping(source = "owner.firstName", target = "ownerFirstName")
    @Mapping(source = "owner.lastName", target = "ownerLastName")
    @Mapping(source = "owner.email", target = "ownerEmail")
    PetDetailDTO petEntityToPetDetailDTO(PetEntity petEntity);


    PetEntity petRegisterDTOtoPetEntity(PetRegisterDTO petRegisterDTO);

}
