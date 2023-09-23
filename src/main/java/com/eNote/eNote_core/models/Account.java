package com.eNote.eNote_core.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Objects;

/**
 * @author Alterranius
 */
@Entity
@Table(name = "account")
public class Account {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Ник не должнен быть пустым")
    @Size(min = 1, max = 20, message = "Ник должно быть от 1 до 20 символов")
    @Column(name = "nickname")
    private String nickname;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 1, max = 20, message = "Имя должно быть от 1 до 20 символов")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Фамилия не должна быть пустым")
    @Size(min = 1, max = 20, message = "Фамилия должна быть от 1 до 20 символов")
    @Column(name = "surname")
    private String surname;

    @NotEmpty(message = "Отчество не должно быть пустым")
    @Size(min = 1, max = 20, message = "Отчество должно быть от 1 до 20 символов")
    @Column(name = "fathername")
    private String fathername;

    @Size(max = 200, message = "Не более 200 символов")
    @Column(name = "description")
    private String description;

    @Size(max = 200, message = "Не более 200 символов")
    @Column(name = "speciality")
    private String speciality;

    @Size(max = 200, message = "Не более 200 символов")
    @Column(name = "portfolio")
    private String portfolio;

    @Size(max = 200, message = "Не более 200 символов")
    @Column(name = "about")
    private String about;

    @Size(max = 200, message = "Не более 200 символов")
    @Column(name = "level")
    private String level;

    @Column(name = "completed")
    private int completed;

    @Column(name = "failed")
    private int failed;

    @Column(name = "workable")
    private int workable;

    @Column(name = "speed")
    private double speed;

    @Column(name = "rate")
    private double rate;

    @Size(max = 200, message = "Не более 200 символов")
    @Column(name = "projects")
    private String projects;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "account")
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private AccountData accountData;

    @OneToMany(mappedBy = "account", cascade = CascadeType.DETACH)
    @JsonIgnore
    private List<Task> tasks;

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_account",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    public Account() {
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

    public Account(int id, String nickname, String name, String surname, String fathername, String description, String speciality, String portfolio, String about, String level, int completed, int failed, int workable, double speed, double rate, String projects, AccountData accountData, List<Task> tasks, List<Role> roles) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id && completed == account.completed && failed == account.failed && workable == account.workable && Double.compare(account.speed, speed) == 0 && Double.compare(account.rate, rate) == 0 && Objects.equals(nickname, account.nickname) && Objects.equals(name, account.name) && Objects.equals(surname, account.surname) && Objects.equals(fathername, account.fathername) && Objects.equals(description, account.description) && Objects.equals(speciality, account.speciality) && Objects.equals(portfolio, account.portfolio) && Objects.equals(about, account.about) && Objects.equals(level, account.level) && Objects.equals(projects, account.projects) && Objects.equals(accountData, account.accountData) && Objects.equals(tasks, account.tasks) && Objects.equals(roles, account.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickname, name, surname, fathername, description, speciality, portfolio, about, level, completed, failed, workable, speed, rate, projects, tasks, roles);
    }

    @Override
    public String toString() {
        return "Account{" +
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
                ", tasks=" + tasks +
                ", roles=" + roles +
                '}';
    }
}
