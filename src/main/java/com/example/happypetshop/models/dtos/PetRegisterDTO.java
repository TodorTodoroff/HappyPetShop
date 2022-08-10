package com.example.happypetshop.models.dtos;


import com.example.happypetshop.models.enums.PetSpeciesEnum;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class PetRegisterDTO {

    @NotEmpty
    private String name;

    @PositiveOrZero
    private int age;

    @NotEmpty
    private String breed;

    @NotEmpty
    private String comment;

    @NotNull
    private PetSpeciesEnum species;

    @PositiveOrZero
    private BigDecimal price;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public PetSpeciesEnum getSpecies() {
        return species;
    }

    public void setSpecies(PetSpeciesEnum species) {
        this.species = species;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
