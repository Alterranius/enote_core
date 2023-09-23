package com.eNote.eNote_core.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.Objects;

/**
 * @author Alterranius
 */
@Entity
@Table(name = "account_data")
public class AccountData {
    @Id
    @Column(name = "account_id")
    private int id;
    @OneToOne
    @MapsId
    @JoinColumn(name = "account_id")
    private Account account;

    @NotEmpty(message = "Логин не должен быть пустым")
    @Size(min = 1, max = 20, message = "Логин должен быть от 1 до 20 символов")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$", message = "Неверный формат логина")
    @Column(name = "username")
    private String username;

    @NotEmpty(message = "Пароль не должен быть пустым")
    @Column(name = "password")
    private String password;

    @Pattern(regexp = "\\d{11}", message = "Неверный формат телефона")
    @Column(name = "phone")
    private String phone;

    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "created_at")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createdAt;

    public AccountData() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountData that = (AccountData) o;
        return id == that.id && Objects.equals(account, that.account) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(phone, that.phone) && Objects.equals(email, that.email) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, account, username, password, phone, email, createdAt);
    }

    @Override
    public String toString() {
        return "AccountData{" +
                "id=" + id +
                ", account=" + account +
                ", username='" + username + '\'' +
                '}';
    }

    public AccountData(int id, Account account, String username, String password, String phone, String email, Date createdAt) {
        this.id = id;
        this.account = account;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.createdAt = createdAt;
    }
}
