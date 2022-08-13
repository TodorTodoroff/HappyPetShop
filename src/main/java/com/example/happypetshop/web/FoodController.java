package com.example.happypetshop.web;

import com.example.happypetshop.services.FoodService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/foods")
    public String getFoods(
            Model model
    ) {

        model.addAttribute("foods",this.foodService.getAllFoods());

        return "foods";
    }

}
