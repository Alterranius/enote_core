package com.eNote.eNote_core.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * @author Alterranius
 */
public class AuthenticationDTO {
    @NotEmpty(message = "Логин не должен быть пустым")
    @Size(min = 1, max = 20, message = "Логин должен быть от 1 до 20 символов")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$", message = "Неверный формат логина")
    private String username;

    @NotEmpty(message = "Пароль не должен быть пустым")
    private String password;

    public AuthenticationDTO() {
    }

    public AuthenticationDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
