package com.example.happypetshop.services;

import com.example.happypetshop.models.dtos.FoodViewDTO;
import com.example.happypetshop.models.mapper.FoodMapper;
import com.example.happypetshop.repositories.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {

    private final FoodRepository foodRepository;
    private final FoodMapper foodMapper;


    public FoodService(FoodRepository foodRepository, FoodMapper foodMapper) {
        this.foodRepository = foodRepository;
        this.foodMapper = foodMapper;
    }


    public List<FoodViewDTO> getAllFoods() {
        List<FoodViewDTO> foodViewDTOS = this.foodRepository.findAll().stream().map(this.foodMapper::foodEntityToFoodDto).toList();

        return foodViewDTOS;
    }
}
