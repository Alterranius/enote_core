package com.eNote.eNote_core.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

/**
 * @author Alterranius
 */

@Document("delegation")
public class Delegation {
    @Id
    private String id;

    @NotEmpty(message = "Тема не должна быть пустой")
    @Size(min = 1, max = 50, message = "Тема должна быть от 1 до 50 символов")
    private String head;

    @NotEmpty(message = "Содержание не должно быть пустым")
    @Size(min = 1, max = 500, message = "Содержание должно быть от 1 до 500 символов")
    private String content;

    private String createdAt;

    private String completedAt;

    private String sender;
    private String receiver;

    private String task;

    public Delegation() {
    }

    public Delegation(String id, String head, String content, String createdAt, String completedAt, String sender, String receiver, String task) {
        this.id = id;
        this.head = head;
        this.content = content;
        this.createdAt = createdAt;
        this.completedAt = completedAt;
        this.sender = sender;
        this.receiver = receiver;
        this.task = task;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(String completedAt) {
        this.completedAt = completedAt;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Delegation that = (Delegation) o;
        return Objects.equals(id, that.id) && Objects.equals(head, that.head) && Objects.equals(content, that.content) && Objects.equals(createdAt, that.createdAt) && Objects.equals(completedAt, that.completedAt) && Objects.equals(sender, that.sender) && Objects.equals(receiver, that.receiver) && Objects.equals(task, that.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, head, content, createdAt, completedAt, sender, receiver, task);
    }

    @Override
    public String toString() {
        return "Delegation{" +
                "id='" + id + '\'' +
                ", head='" + head + '\'' +
                ", content='" + content + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", completedAt='" + completedAt + '\'' +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", task='" + task + '\'' +
                '}';
    }
}
