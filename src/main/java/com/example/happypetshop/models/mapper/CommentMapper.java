package com.example.happypetshop.models.mapper;


import com.example.happypetshop.models.dtos.CommentDisplayViewDTO;
import com.example.happypetshop.models.entities.CommentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentDisplayViewDTO foodEntityToFoodDto(CommentEntity commentEntity);

}
