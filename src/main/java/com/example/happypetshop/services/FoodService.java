package com.example.happypetshop.services;

import com.example.happypetshop.models.dtos.FoodViewDTO;
import com.example.happypetshop.models.mapper.FoodMapper;
import com.example.happypetshop.repositories.FoodRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class FoodService {

    private final FoodRepository foodRepository;
    private final FoodMapper foodMapper;


    public FoodService(FoodRepository foodRepository, FoodMapper foodMapper) {
        this.foodRepository = foodRepository;
        this.foodMapper = foodMapper;
    }


    @Cacheable("foods")
    public List<FoodViewDTO> getAllFoods() {
        return this.foodRepository.findAll().stream().map(this.foodMapper::foodEntityToFoodDto).toList();
    }


    @CacheEvict(cacheNames="foods", allEntries=true)
    public void refresh() {
    }

    @Scheduled(cron = "0 0 0/24 ? * * *")
    public void evictAllCaches(){
        refresh();
    }

}
