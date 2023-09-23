package com.eNote.eNote_core.controllers;

import com.eNote.eNote_core.services.config.UserTarget;
import com.eNote.eNote_core.interfaces.ProjectManager;
import com.eNote.eNote_core.interfaces.ProjectsShower;
import com.eNote.eNote_core.models.Methodology;
import com.eNote.eNote_core.models.Project;
import com.eNote.eNote_core.services.unit.util.ProjectValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * @author Alterranius
 */
@RestController
@RequestMapping("/project")
public class ProjectController {
    private final ProjectManager projectManager;
    private final ProjectsShower projectsShower;
    private final ProjectValidator projectValidator;
    private final UserTarget userTarget;

    @Autowired
    public ProjectController(ProjectManager projectManager, ProjectsShower projectsShower, ProjectValidator projectValidator, UserTarget userTarget) {
        this.projectManager = projectManager;
        this.projectsShower = projectsShower;
        this.projectValidator = projectValidator;
        this.userTarget = userTarget;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createProject(@RequestBody Project project, BindingResult bindingResult) {
        projectValidator.validate(project, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.internalServerError().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        projectManager.create(project, userTarget.getTokenUser().getId());
        return ResponseEntity.created(URI.create("")).body(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateProject(@RequestBody Project project,
                                                       @PathVariable("id") int id,
                                                       BindingResult bindingResult) {
        projectValidator.validate(project, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.internalServerError().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        projectManager.update(id, project);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProject(@PathVariable("id") int id) {
        try {
            projectManager.delete(id);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @GetMapping
    @ResponseBody
    public List<Project> getProjects() {
        try {
            return projectsShower.getProjects(userTarget.getTokenUser().getId());
        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping("/leave/{id}")
    public ResponseEntity<HttpStatus> leaveProject(@PathVariable("id") int id) {
        try {
            projectManager.leave(id, userTarget.getTokenUser().getId());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @GetMapping("/methodologies")
    @ResponseBody
    public List<Methodology> getMethodologies() {
        try {
            return projectsShower.getMethodologies();
        } catch (Exception e) {
            return null;
        }
    }
}
