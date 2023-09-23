package com.eNote.eNote_core.dto;

import com.eNote.eNote_core.models.Account;

/**
 * @author Alterranius
 */
public class AppealDTO {
    private String id;
    private String head;
    private String content;
    private String createdAt;
    private String isResponse;
    private Account sender;
    private Account receiver;

    public AppealDTO() {
    }

    public AppealDTO(String id, String head, String content, String createdAt, String isResponse, Account sender, Account receiver) {
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
}
