package com.eNote.eNote_core.models;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Alterranius
 */
@Document("invite")
public class Invite {
    @Id
    private String id;

    private String createdAt;

    private String status;

    private String acted;

    @NotEmpty(message = "Аккаунт не должен быть пустым")
    private String account;

    @NotEmpty(message = "Проект не должен быть пустым")
    private String project;

    public Invite() {
    }

    public Invite(String id, String createdAt, String status, String acted, String account, String project) {
        this.id = id;
        this.createdAt = createdAt;
        this.status = status;
        this.acted = acted;
        this.account = account;
        this.project = project;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getActed() {
        return acted;
    }

    public void setActed(String acted) {
        this.acted = acted;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }


}
