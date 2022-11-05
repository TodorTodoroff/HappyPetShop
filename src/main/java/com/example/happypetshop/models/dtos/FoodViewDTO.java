package com.example.happypetshop.models.dtos;

import com.example.happypetshop.models.enums.FoodBrandEnum;


import java.math.BigDecimal;

public class FoodViewDTO {

    private FoodBrandEnum brand;


    private String type;


    private String description;


    private BigDecimal price;

    private String pictureUrl;


    public FoodViewDTO() {
    }

    public String getFoodHeaderDetails(){
        return brand.name();
    }


    public FoodBrandEnum getBrand() {
        return brand;
    }

    public void setBrand(FoodBrandEnum brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
