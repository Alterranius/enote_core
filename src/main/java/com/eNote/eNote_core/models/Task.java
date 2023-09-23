package com.eNote.eNote_core.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.Objects;

/**
 * @author Alterranius
 */
@Entity
@Table(name = "task")
public class Task {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Название не должнен быть пустым")
    @Size(min = 1, max = 50, message = "Название должно быть от 1 до 50 символов")
    @Column(name = "name")
    private String name;

    @Size(min = 1, max = 20, message = "Код должнен быть от 1 до 20 символов")
    @Column(name = "code")
    private String code;

    @Size(max = 200, message = "Не более 200 символов")
    @Column(name = "description")
    private String description;

    @Column(name = "priority")
    private int priority;

    @Column(name = "created_at")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "delegated_at")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date delegatedAt;

    @Column(name = "completed_at")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date completedAt;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @JsonProperty("TaskCategory")
    private TaskCategory category;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    @JsonProperty("TaskStatus")
    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    @JsonProperty("TaskType")
    private TaskType type;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;

    public Task() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getDelegatedAt() {
        return delegatedAt;
    }

    public void setDelegatedAt(Date delegatedAt) {
        this.delegatedAt = delegatedAt;
    }

    public Date getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Date completedAt) {
        this.completedAt = completedAt;
    }

    public TaskCategory getCategory() {
        return category;
    }

    public void setCategory(TaskCategory category) {
        this.category = category;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && priority == task.priority && Objects.equals(name, task.name) && Objects.equals(code, task.code) && Objects.equals(description, task.description) && Objects.equals(createdAt, task.createdAt) && Objects.equals(delegatedAt, task.delegatedAt) && Objects.equals(completedAt, task.completedAt) && Objects.equals(category, task.category) && Objects.equals(status, task.status) && Objects.equals(type, task.type) && Objects.equals(account, task.account) && Objects.equals(team, task.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, description, priority, createdAt, delegatedAt, completedAt, category, status, type, account, team);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
