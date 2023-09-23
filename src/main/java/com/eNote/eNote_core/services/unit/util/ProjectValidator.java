package com.eNote.eNote_core.services.unit.util;

import com.eNote.eNote_core.models.Project;
import com.eNote.eNote_core.services.unit.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Alterranius
 */
@Component
public class ProjectValidator implements Validator {
    private final ProjectService projectService;

    @Autowired
    public ProjectValidator(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Project.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Project project = (Project) target;
    }
}
