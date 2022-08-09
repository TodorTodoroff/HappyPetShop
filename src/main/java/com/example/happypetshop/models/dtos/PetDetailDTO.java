package com.example.happypetshop.models.dtos;

import com.example.happypetshop.models.enums.PetSpeciesEnum;

import java.math.BigDecimal;

public class PetDetailDTO {

    private Long id;

    private String name;

    private int age;

    private String breed;

    private String pictureUrl;

    private String comment;

    private PetSpeciesEnum species;

    private BigDecimal price;

    private String ownerFirstName;

    private String ownerLastName;

    private String ownerEmail;

    public PetDetailDTO() {
    }

    public String getPetHeaderDetails(){
        return name + " " + breed + " " + price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getOwnerFirstName() {
        return ownerFirstName;
    }

    public void setOwnerFirstName(String ownerFirstName) {
        this.ownerFirstName = ownerFirstName;
    }

    public String getOwnerLastName() {
        return ownerLastName;
    }

    public void setOwnerLastName(String ownerLastName) {
        this.ownerLastName = ownerLastName;
    }


}
