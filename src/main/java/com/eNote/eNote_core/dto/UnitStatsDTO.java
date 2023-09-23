package com.eNote.eNote_core.dto;

import java.util.Map;

/**
 * @author Alterranius
 */
public class UnitStatsDTO {
    private String completed;
    private String failed;
    private String inWork;
    private String speed;
    private String week;
    private String effectivency;
    private Map<String, String> taskAccounts;
    private Map<String, String> completedTasks;

    public UnitStatsDTO() {
    }

    public UnitStatsDTO(String completed, String failed, String inWork, String speed, String week, String effectivency, Map<String, String> taskAccounts, Map<String, String> completedTasks) {
        this.completed = completed;
        this.failed = failed;
        this.inWork = inWork;
        this.speed = speed;
        this.week = week;
        this.effectivency = effectivency;
        this.taskAccounts = taskAccounts;
        this.completedTasks = completedTasks;
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

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
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

    public Map<String, String> getCompletedTasks() {
        return completedTasks;
    }

    public void setCompletedTasks(Map<String, String> completedTasks) {
        this.completedTasks = completedTasks;
    }
}
