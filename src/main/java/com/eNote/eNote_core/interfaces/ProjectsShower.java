package com.eNote.eNote_core.interfaces;

import com.eNote.eNote_core.models.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Alterranius
 */
public interface ProjectsShower {
    List<Depart> getDeparts(int id);
    List<Task> getActiveTasks(int id);
    Optional<Project> getProjectDetails(int id);
    List<Project> getProjects(int id);
    List<Methodology> getMethodologies();
}
