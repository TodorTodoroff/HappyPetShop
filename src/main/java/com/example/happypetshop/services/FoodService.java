package com.example.happypetshop.services;

import com.example.happypetshop.models.entities.FoodEntity;
import com.example.happypetshop.repositories.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {

    private final FoodRepository foodRepository;


    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }


    public List<FoodEntity> getAllFoods() {
       return this.foodRepository.findAll();
    }
}
