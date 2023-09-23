package com.eNote.eNote_core.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Date;

/**
 * @author Alterranius
 */
public class AccountDataDTO {
    private int id;
    @NotEmpty(message = "Логин не должен быть пустым")
    @Size(min = 1, max = 20, message = "Логин должен быть от 1 до 20 символов")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$", message = "Неверный формат логина")
    private String username;

    @NotEmpty(message = "Пароль не должен быть пустым")
    private String password;

    @Pattern(regexp = "", message = "Неверный формат телефона")
    private String phone;

    @Email
    private String email;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createdAt;

    public AccountDataDTO() {
    }

    public AccountDataDTO(int id, String username, String password, String phone, String email, Date createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
