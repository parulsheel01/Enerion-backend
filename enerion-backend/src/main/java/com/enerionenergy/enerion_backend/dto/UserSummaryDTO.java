package com.enerionenergy.enerion_backend.dto;

public class UserSummaryDTO {
    
    private String name;
    private String email;

    public UserSummaryDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
