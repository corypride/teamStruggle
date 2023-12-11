package main.java.com.example.models.dto;

public class RegisterFormDTO extends LoginFormDTO {

    @NotNull(message = "Password is required")
    @NotBlank(message = "Password is required")
    @Size(min = 7, max = 30, message = "Invalid password. Must be between 7 and 30 characters.")
    private String verifyPassword;

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }
}
