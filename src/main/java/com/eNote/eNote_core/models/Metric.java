package com.eNote.eNote_core.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Objects;

/**
 * @author Alterranius
 */
@Entity
@Table(name = "metric")
public class Metric {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Название не должно быть пустым")
    @Size(min = 1, max = 50, message = "Название должно быть от 1 до 50 символов")
    @Column(name = "name")
    private String name;

    @Size(min = 1, max = 20, message = "Обозначение должно быть от 1 до 20 символов")
    @Column(name = "sign")
    private String sign;

    @Size(max = 200, message = "Не более 200 символов")
    @Column(name = "description")
    private String description;

    @Size(max = 200, message = "Не больше 200 символов")
    @Column(name = "formulae")
    private String formulae;

    @ManyToMany
    @JoinTable(
            name = "metric_project",
            joinColumns = @JoinColumn(name = "metric_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private List<Project> projects;

    @ManyToMany
    @JoinTable(
            name = "metric_depart",
            joinColumns = @JoinColumn(name = "metric_id"),
            inverseJoinColumns = @JoinColumn(name = "depart_id"))
    private List<Depart> departs;

    @ManyToMany
    @JoinTable(
            name = "metric_team",
            joinColumns = @JoinColumn(name = "metric_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id"))
    private List<Team> teams;

    @ManyToMany
    @JoinTable(
            name = "indicator_metric",
            joinColumns = @JoinColumn(name = "metric_id"),
            inverseJoinColumns = @JoinColumn(name = "indicator_id"))
    private List<Indicator> indicators;

    public Metric() {
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

    public String getSign() {
        return sign;
    }

    public void setSign(String sing) {
        this.sign = sing;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFormulae() {
        return formulae;
    }

    public void setFormulae(String formulae) {
        this.formulae = formulae;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Depart> getDeparts() {
        return departs;
    }

    public void setDeparts(List<Depart> departs) {
        this.departs = departs;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Metric metric = (Metric) o;
        return id == metric.id && Objects.equals(name, metric.name) && Objects.equals(sign, metric.sign) && Objects.equals(description, metric.description) && Objects.equals(formulae, metric.formulae) && Objects.equals(projects, metric.projects) && Objects.equals(departs, metric.departs) && Objects.equals(teams, metric.teams);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sign, description, formulae, projects, departs, teams);
    }

    @Override
    public String toString() {
        return "Metric{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sing='" + sign + '\'' +
                '}';
    }
}
