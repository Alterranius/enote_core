package com.eNote.eNote_core.services.task.util;

import com.eNote.eNote_core.models.Task;
import com.eNote.eNote_core.models.Team;
import com.eNote.eNote_core.services.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Alterranius
 */
@Component
public class TaskValidator implements Validator {
    private final TaskService taskService;

    @Autowired
    public TaskValidator(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Team.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Task task = (Task) target;
    }
}
