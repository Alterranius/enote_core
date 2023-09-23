package com.eNote.eNote_core.dto;

import java.util.Map;

/**
 * @author Alterranius
 */
public class AccountStatsDTO {
    private String completed;
    private String failed;
    private String inWork;
    private String speed;
    private String delegationsCompleted;
    private String effectivency;
    private Map<String, String> taskAccounts;

    public AccountStatsDTO() {
    }

    public AccountStatsDTO(String completed, String failed, String inWork, String speed, String delegationsCompleted, String effectivency, Map<String, String> taskAccounts) {
        this.completed = completed;
        this.failed = failed;
        this.inWork = inWork;
        this.speed = speed;
        this.delegationsCompleted = delegationsCompleted;
        this.effectivency = effectivency;
        this.taskAccounts = taskAccounts;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    public String getFailed() {
        return failed;
    }

    public void setFailed(String failed) {
        this.failed = failed;
    }

    public String getInWork() {
        return inWork;
    }

    public void setInWork(String inWork) {
        this.inWork = inWork;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getDelegationsCompleted() {
        return delegationsCompleted;
    }

    public void setDelegationsCompleted(String delegationsCompleted) {
        this.delegationsCompleted = delegationsCompleted;
    }

    public String getEffectivency() {
        return effectivency;
    }

    public void setEffectivency(String effectivency) {
        this.effectivency = effectivency;
    }

    public Map<String, String> getTaskAccounts() {
        return taskAccounts;
    }

    public void setTaskAccounts(Map<String, String> taskAccounts) {
        this.taskAccounts = taskAccounts;
    }
}
