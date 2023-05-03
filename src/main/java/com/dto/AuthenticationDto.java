package com.dto;

import lombok.Data;

@Data
public class AuthenticationDto {
    private String email;
    private String password;

    public AuthenticationDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
