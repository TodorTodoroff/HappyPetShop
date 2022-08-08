package com.example.happypetshop.models.dtos;

import javax.validation.constraints.Email;

public class UserUpdateUsernameDTO {

    @Email
    private String username;

    @Email
    private String repeatUsername;

    public UserUpdateUsernameDTO() {
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRepeatUsername() {
        return repeatUsername;
    }

    public void setRepeatUsername(String repeatUsername) {
        this.repeatUsername = repeatUsername;
    }
}
