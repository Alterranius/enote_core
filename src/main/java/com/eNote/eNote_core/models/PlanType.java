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
@Table(name = "plan_type")
public class PlanType {
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

    @OneToMany(mappedBy = "type")
    @JsonIgnore
    private List<Plan> plans;

    public PlanType() {
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

    public List<Plan> getPlans() {
        return plans;
    }

    public void setPlans(List<Plan> plans) {
        this.plans = plans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanType planType = (PlanType) o;
        return id == planType.id && Objects.equals(name, planType.name) && Objects.equals(description, planType.description) && Objects.equals(plans, planType.plans);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, plans);
    }

    @Override
    public String toString() {
        return "PlanType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
