package com.example.happypetshop.models.dtos;

public class AdminCommandDTO {

    private boolean toBeAdmin;

    private String email;

    public AdminCommandDTO() {
    }

    public boolean isToBeAdmin() {
        return toBeAdmin;
    }

    public void setToBeAdmin(boolean toBeAdmin) {
        this.toBeAdmin = toBeAdmin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
