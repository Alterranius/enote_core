package com.eNote.eNote_core.dto;

import com.eNote.eNote_core.models.AccountData;
import com.eNote.eNote_core.models.Role;
import com.eNote.eNote_core.models.Task;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * @author Alterranius
 */
public class AccountDTO {
    private int id;

    @NotEmpty(message = "Ник не должнен быть пустым")
    @Size(min = 1, max = 20, message = "Ник должно быть от 1 до 20 символов")
    private String nickname;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 1, max = 20, message = "Имя должно быть от 1 до 20 символов")
    private String name;

    @NotEmpty(message = "Фамилия не должна быть пустым")
    @Size(min = 1, max = 20, message = "Фамилия должна быть от 1 до 20 символов")
    private String surname;

    @NotEmpty(message = "Отчество не должно быть пустым")
    @Size(min = 1, max = 20, message = "Отчество должно быть от 1 до 20 символов")
    private String fathername;

    @Size(max = 200, message = "Не более 200 символов")
    private String description;

    @Size(max = 200, message = "Не более 200 символов")
    private String speciality;

    @Size(max = 200, message = "Не более 200 символов")
    private String portfolio;

    @Size(max = 200, message = "Не более 200 символов")
    private String about;

    @Size(max = 200, message = "Не более 200 символов")
    private String level;

    private int completed;

    private int failed;

    private int workable;

    private double speed;

    private double rate;

    @Size(max = 200, message = "Не более 200 символов")
    private String projects;

    private AccountData accountData;

    private List<Task> tasks;

    private List<Role> roles;

    public AccountDTO() {
    }

    public AccountDTO(int id, String nickname, String name, String surname, String fathername, String description, String speciality, String portfolio, String about, String level, int completed, int failed, int workable, double speed, double rate, String projects, AccountData accountData, List<Task> tasks, List<Role> roles) {
        this.id = id;
        this.nickname = nickname;
        this.name = name;
        this.surname = surname;
        this.fathername = fathername;
        this.description = description;
        this.speciality = speciality;
        this.portfolio = portfolio;
        this.about = about;
        this.level = level;
        this.completed = completed;
        this.failed = failed;
        this.workable = workable;
        this.speed = speed;
        this.rate = rate;
        this.projects = projects;
        this.accountData = accountData;
        this.tasks = tasks;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(String portfolio) {
        this.portfolio = portfolio;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public int getFailed() {
        return failed;
    }

    public void setFailed(int failed) {
        this.failed = failed;
    }

    public int getWorkable() {
        return workable;
    }

    public void setWorkable(int workable) {
        this.workable = workable;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    public AccountData getAccountData() {
        return accountData;
    }

    public void setAccountData(AccountData accountData) {
        this.accountData = accountData;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", fathername='" + fathername + '\'' +
                ", description='" + description + '\'' +
                ", speciality='" + speciality + '\'' +
                ", portfolio='" + portfolio + '\'' +
                ", about='" + about + '\'' +
                ", level='" + level + '\'' +
                ", completed=" + completed +
                ", failed=" + failed +
                ", workable=" + workable +
                ", speed=" + speed +
                ", rate=" + rate +
                ", projects='" + projects + '\'' +
                ", accountData=" + accountData +
                ", tasks=" + tasks +
                ", roles=" + roles +
                '}';
    }
}
