package com.eNote.eNote_core.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * @author Alterranius
 */
@Entity
@Table(name = "plan_content")
public class PlanContent implements Comparable<PlanContent> {
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
    @JsonProperty("Description")
    private String description;

    @Size(max = 200, message = "Не более 200 символов")
    @Column(name = "aim")
    private String aim;

    @Column(name = "position")
    @JsonProperty("Position")
    private int position;

    @ManyToOne
    @JoinColumn(name = "plan_id", referencedColumnName = "id")
    @JsonIgnore
    private Plan plan;

    public PlanContent() {
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

    public String getAim() {
        return aim;
    }

    public void setAim(String aim) {
        this.aim = aim;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanContent that = (PlanContent) o;
        return id == that.id && position == that.position && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(aim, that.aim) && Objects.equals(plan, that.plan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, aim, position, plan);
    }

    @Override
    public String toString() {
        return "PlanContent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(PlanContent o) {
        return Integer.compare(o.position, this.position);
    }
}
