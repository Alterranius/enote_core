package com.eNote.eNote_core.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

/**
 * @author Alterranius
 */
@Document("appeal")
public class Appeal {
    @Id
    private String id;

    @NotEmpty(message = "Заголовок не должен быть пустым")
    @Size(min = 1, max = 50, message = "Заголовок должен быть от 1 до 50 символов")
    private String head;

    @NotEmpty(message = "Содержание не должнен быть пустым")
    @Size(min = 1, max = 500, message = "Содержание должно быть от 1 до 500 символов")
    private String content;

    private String createdAt;

    private String isResponse;

    @NotEmpty(message = "Аккаунт не должен быть пустым")
    private String sender;

    @NotEmpty(message = "Аккаунт не должен быть пустой")
    private String receiver;

    public Appeal() {
    }

    public Appeal(String id, String head, String content, String createdAt, String isResponse, String sender, String receiver) {
        this.id = id;
        this.head = head;
        this.content = content;
        this.createdAt = createdAt;
        this.isResponse = isResponse;
        this.sender = sender;
        this.receiver = receiver;
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

    public String getIsResponse() {
        return isResponse;
    }

    public void setIsResponse(String isResponse) {
        this.isResponse = isResponse;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appeal appeal = (Appeal) o;
        return Objects.equals(id, appeal.id) && Objects.equals(head, appeal.head) && Objects.equals(content, appeal.content) && Objects.equals(createdAt, appeal.createdAt) && Objects.equals(isResponse, appeal.isResponse) && Objects.equals(sender, appeal.sender) && Objects.equals(receiver, appeal.receiver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, head, content, createdAt, isResponse, sender, receiver);
    }

    @Override
    public String toString() {
        return "Appeal{" +
                "id='" + id + '\'' +
                ", head='" + head + '\'' +
                ", content='" + content + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", isResponse='" + isResponse + '\'' +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                '}';
    }
}
