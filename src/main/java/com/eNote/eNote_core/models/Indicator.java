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
@Table(name = "indicator")
public class Indicator {
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

    @ManyToMany(mappedBy = "indicators")
    private List<Metric> metrics;

    @ManyToMany(mappedBy = "indicatorsJoin")
    private List<Indicator> indicators;

    @ManyToMany
    @JoinTable(
            name = "indicator_pool",
            joinColumns = @JoinColumn(name = "indicator_first_id"),
            inverseJoinColumns = @JoinColumn(name = "indicator_second_id"))
    private List<Indicator> indicatorsJoin;

    @ManyToMany
    @JoinTable(
            name = "indicator_project",
            joinColumns = @JoinColumn(name = "indicator_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private List<Project> projects;

    @ManyToMany
    @JoinTable(
            name = "indicator_depart",
            joinColumns = @JoinColumn(name = "indicator_id"),
            inverseJoinColumns = @JoinColumn(name = "depart_id"))
    private List<Depart> departs;

    @ManyToMany
    @JoinTable(
            name = "indicator_team",
            joinColumns = @JoinColumn(name = "indicator_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id"))
    private List<Team> teams;

    public Indicator() {
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

    public void setSign(String sign) {
        this.sign = sign;
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

    public List<Metric> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<Metric> metrics) {
        this.metrics = metrics;
    }

    public List<Indicator> getIndicators() {
        return indicators;
    }

    public void setIndicators(List<Indicator> indicators) {
        this.indicators = indicators;
    }

    public List<Indicator> getIndicatorsJoin() {
        return indicatorsJoin;
    }

    public void setIndicatorsJoin(List<Indicator> indicatorsJoin) {
        this.indicatorsJoin = indicatorsJoin;
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
        Indicator indicator = (Indicator) o;
        return id == indicator.id && Objects.equals(name, indicator.name) && Objects.equals(sign, indicator.sign) && Objects.equals(description, indicator.description) && Objects.equals(formulae, indicator.formulae) && Objects.equals(metrics, indicator.metrics) && Objects.equals(indicators, indicator.indicators) && Objects.equals(indicatorsJoin, indicator.indicatorsJoin) && Objects.equals(projects, indicator.projects) && Objects.equals(departs, indicator.departs) && Objects.equals(teams, indicator.teams);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sign, description, formulae, metrics, indicators, indicatorsJoin, projects, departs, teams);
    }

    @Override
    public String toString() {
        return "Indicator{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sing='" + sign + '\'' +
                '}';
    }
}
