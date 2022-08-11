package com.example.happypetshop.models.dtos;


import com.example.happypetshop.models.entities.UserRoleEntity;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsAdminDTO {

    private String email;

    private String firstName;

    private String lastName;

    private boolean isActive;

    private List<UserRoleEntity> userRoles = new ArrayList<>();

    public UserDetailsAdminDTO() {
    }

    public void addUserRole(UserRoleEntity userRole){
        this.userRoles.add(userRole);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<UserRoleEntity> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRoleEntity> userRoles) {
        this.userRoles = userRoles;
    }
}
