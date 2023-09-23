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
@Table(name = "depart")
public class Depart {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Название не должно быть пустым")
    @Size(min = 1, max = 50, message = "Название должно быть от 1 до 50 символов")
    @Column(name = "name")
    private String name;

    @Size(max = 200, message = "Не более 200 символов")
    @Column(name = "description")
    private String description;

    @Size(max = 100, message = "Не больше 100 символов")
    @Column(name = "product")
    private String product;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;

    @OneToMany(mappedBy = "depart", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Role> roles;

    @OneToMany(mappedBy = "depart", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Team> teams;

    @OneToMany(mappedBy = "depart", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Plan> plans;

    @ManyToMany(mappedBy = "departs")
    @JsonIgnore
    private List<Indicator> indicators;

    @ManyToMany(mappedBy = "departs")
    @JsonIgnore
    private List<Metric> metrics;

    public Depart() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Plan> getPlans() {
        return plans;
    }

    public void setPlans(List<Plan> plans) {
        this.plans = plans;
    }

    public List<Indicator> getIndicators() {
        return indicators;
    }

    public void setIndicators(List<Indicator> indicators) {
        this.indicators = indicators;
    }

    public List<Metric> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<Metric> metrics) {
        this.metrics = metrics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Depart depart = (Depart) o;
        return id == depart.id && Objects.equals(name, depart.name) && Objects.equals(description, depart.description) && Objects.equals(product, depart.product) && Objects.equals(project, depart.project) && Objects.equals(roles, depart.roles) && Objects.equals(teams, depart.teams) && Objects.equals(plans, depart.plans) && Objects.equals(indicators, depart.indicators) && Objects.equals(metrics, depart.metrics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, product, project, roles, teams, plans, indicators, metrics);
    }

    @Override
    public String toString() {
        return "Depart{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", project=" + project +
                '}';
    }
}
