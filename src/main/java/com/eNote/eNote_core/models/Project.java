package com.eNote.eNote_core.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Alterranius
 */
@Entity
@Table(name = "project")
public class Project {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Название не должно быть пустым")
    @Size(min = 1, max = 50, message = "Название должно быть от 1 до 50 символов")
    @Column(name = "name")
    private String name;

    @Size(min = 1, max = 5, message = "Префикс должен быть от 1 до 5 символов")
    @Column(name = "prefix")
    private String prefix;

    @NotEmpty(message = "Вид деятельности не должен быть пустым")
    @Size(min = 1, max = 50, message = "Вид деятельности должен быть от 1 до 50 символов")
    @Column(name = "specialization")
    private String specialization;

    @Column(name = "created_at")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "deadline")
    @Temporal(value = TemporalType.DATE)
    private Date deadline;

    @Column(name = "hard_deadline")
    @Temporal(value = TemporalType.DATE)
    private Date hardDeadline;

    @Size(max = 100, message = "Не больше 100 символов")
    @Column(name = "mission")
    private String mission;

    @Size(max = 100, message = "Не больше 100 символов")
    @Column(name = "product")
    private String product;

    @ManyToOne
    @JoinColumn(name = "methodology_id", referencedColumnName = "id")
    private Methodology methodology;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Zone> zones;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Role> roles;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Depart> departs;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Plan> plans;

    @ManyToMany(mappedBy = "projects")
    @JsonIgnore
    private List<Indicator> indicators;

    @ManyToMany(mappedBy = "projects")
    @JsonIgnore
    private List<Metric> metrics;

    public Project() {
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

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getHardDeadline() {
        return hardDeadline;
    }

    public void setHardDeadline(Date hardDeadline) {
        this.hardDeadline = hardDeadline;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Methodology getMethodology() {
        return methodology;
    }

    public void setMethodology(Methodology methodology) {
        this.methodology = methodology;
    }

    public List<Zone> getZones() {
        return zones;
    }

    public void setZones(List<Zone> zones) {
        this.zones = zones;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Depart> getDeparts() {
        return departs;
    }

    public void setDeparts(List<Depart> departs) {
        this.departs = departs;
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
        Project project = (Project) o;
        return id == project.id && Objects.equals(name, project.name) && Objects.equals(prefix, project.prefix) && Objects.equals(specialization, project.specialization) && Objects.equals(createdAt, project.createdAt) && Objects.equals(deadline, project.deadline) && Objects.equals(hardDeadline, project.hardDeadline) && Objects.equals(mission, project.mission) && Objects.equals(product, project.product) && Objects.equals(methodology, project.methodology) && Objects.equals(zones, project.zones) && Objects.equals(roles, project.roles) && Objects.equals(departs, project.departs) && Objects.equals(plans, project.plans) && Objects.equals(indicators, project.indicators) && Objects.equals(metrics, project.metrics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, prefix, specialization, createdAt, deadline, hardDeadline, mission, product, methodology, zones, roles, departs, plans, indicators, metrics);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", prefix='" + prefix + '\'' +
                '}';
    }
}