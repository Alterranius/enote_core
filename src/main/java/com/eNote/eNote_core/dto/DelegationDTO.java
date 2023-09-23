package com.eNote.eNote_core.dto;

import com.eNote.eNote_core.models.Account;
import com.eNote.eNote_core.models.Task;

/**
 * @author Alterranius
 */
public class DelegationDTO {
    private String id;
    private String head;
    private String content;
    private String createdAt;
    private String completedAt;
    private Account sender;
    private Account receiver;
    private Task task;

    public DelegationDTO() {
    }

    public DelegationDTO(String id, String head, String content, String createdAt, String completedAt, Account sender, Account receiver, Task task) {
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

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public Account getReceiver() {
        return receiver;
    }

    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
