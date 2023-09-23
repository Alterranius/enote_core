package com.eNote.eNote_core.dto;

import com.eNote.eNote_core.models.Account;
import com.eNote.eNote_core.models.Task;

import java.util.Date;

/**
 * @author Alterranius
 */
public class CommentDTO {
    private String id;
    private String head;
    private String content;
    private Date createdAt;
    private Account account;
    private Task task;

    public CommentDTO() {
    }

    public CommentDTO(String id, String head, String content, Date createdAt, Account account, Task task) {
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
