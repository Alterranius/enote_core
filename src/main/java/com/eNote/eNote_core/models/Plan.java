package com.eNote.eNote_core.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Objects;

/**
 * @author Alterranius
 */
@Entity
@Table(name = "plan")
public class Plan {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Название не должно быть пустым")
    @Size(min = 1, max = 50, message = "Название должно быть от 1 до 50 символов")
    @Column(name = "name")
    private String name;

    @Size(max = 200, message = "Не более 200 символов")
    @Column(name = "aim")
    private String aim;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    @JsonProperty("PlanType")
    private PlanType type;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "depart_id", referencedColumnName = "id")
    private Depart depart;

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
    private List<PlanContent> content;

    public Plan() {
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

    public String getAim() {
        return aim;
    }

    public void setAim(String aim) {
        this.aim = aim;
    }

    public PlanType getType() {
        return type;
    }

    public void setType(PlanType type) {
        this.type = type;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Depart getDepart() {
        return depart;
    }

    public void setDepart(Depart depart) {
        this.depart = depart;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<PlanContent> getContent() {
        return content;
    }

    public void setContent(List<PlanContent> content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plan plan = (Plan) o;
        return id == plan.id && Objects.equals(name, plan.name) && Objects.equals(aim, plan.aim) && Objects.equals(type, plan.type) && Objects.equals(project, plan.project) && Objects.equals(depart, plan.depart) && Objects.equals(team, plan.team) && Objects.equals(content, plan.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, aim, type, project, depart, team, content);
    }

    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", aim='" + aim + '\'' +
                '}';
    }
}
