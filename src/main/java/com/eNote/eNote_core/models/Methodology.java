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
@Table(name = "methodology")
public class Methodology {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Название не должно быть пустым")
    @Size(min = 1, max = 50, message = "Название должно быть от 1 до 50 символов")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Тезис не должен быть пустым")
    @Size(min = 1, max = 50, message = "Тезис должен быть от 1 до 50 символов")
    @Column(name = "thesis")
    private String thesis;

    @Size(max = 200, message = "Не более 200 символов")
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "methodology")
    @JsonIgnore
    private List<Project> projects;

    public Methodology() {
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

    public String getThesis() {
        return thesis;
    }

    public void setThesis(String thesis) {
        this.thesis = thesis;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Methodology that = (Methodology) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(thesis, that.thesis) && Objects.equals(description, that.description) && Objects.equals(projects, that.projects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, thesis, description, projects);
    }

    @Override
    public String toString() {
        return "Methodology{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", thesis='" + thesis + '\'' +
                '}';
    }
}
