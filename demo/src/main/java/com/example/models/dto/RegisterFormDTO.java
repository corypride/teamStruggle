package com.example.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegisterFormDTO extends LoginFormDTO {

//    @NotNull(message = "Password is required")
//    @NotBlank(message = "Password is required")
//    @Size(min = 7, max = 30, message = "Invalid password. Must be between 7 and 30 characters.")
    private String verifyPassword;

    public String getVerifyPassword() {

        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {

        this.verifyPassword = verifyPassword;
    }
}
