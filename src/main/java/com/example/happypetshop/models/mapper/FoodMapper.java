package com.example.happypetshop.models.mapper;

import com.example.happypetshop.models.dtos.FoodViewDTO;
import com.example.happypetshop.models.entities.FoodEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface FoodMapper {

    FoodViewDTO foodEntityToFoodDto(FoodEntity foodView);

}
