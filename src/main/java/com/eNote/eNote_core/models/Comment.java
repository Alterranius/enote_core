package com.eNote.eNote_core.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

/**
 * @author Alterranius
 */
@Document("comment")
public class Comment {
    @Id
    private String id;

    @NotEmpty(message = "Заголовок не должен быть пустым")
    @Size(min = 1, max = 50, message = "Заголовок должен быть от 1 до 50 символов")
    private String head;

    @NotEmpty(message = "Содержание не должнен быть пустым")
    @Size(min = 1, max = 50, message = "Содержание должно быть от 1 до 50 символов")
    private String content;

    private String createdAt;

    private String account;

    private String task;

    public Comment() {
    }

    public Comment(String id, String head, String content, String createdAt, String account, String task) {
        this.id = id;
        this.head = head;
        this.content = content;
        this.createdAt = createdAt;
        this.account = account;
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) && Objects.equals(head, comment.head) && Objects.equals(content, comment.content) && Objects.equals(createdAt, comment.createdAt) && Objects.equals(account, comment.account) && Objects.equals(task, comment.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, head, content, createdAt, account, task);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", head='" + head + '\'' +
                ", content='" + content + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", account='" + account + '\'' +
                ", task='" + task + '\'' +
                '}';
    }
}
