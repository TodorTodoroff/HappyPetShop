package com.example.happypetshop.models.dtos;


import com.example.happypetshop.models.enums.PetSpeciesEnum;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class PetRegisterDTO {

    @NotEmpty
    private String name;

    @PositiveOrZero
    private int age;

    @NotEmpty
    private String breed;

    @NotEmpty
    private String description;

    @NotNull
    private PetSpeciesEnum speciesEnum;

    private String pictureUrl;

    public PetRegisterDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PetSpeciesEnum getSpeciesEnum() {
        return speciesEnum;
    }

    public void setSpeciesEnum(PetSpeciesEnum speciesEnum) {
        this.speciesEnum = speciesEnum;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

}
