package com.eNote.eNote_core.controllers;

import com.eNote.eNote_core.services.config.UserTarget;
import com.eNote.eNote_core.interfaces.*;
import com.eNote.eNote_core.models.*;
import com.eNote.eNote_core.services.task.util.TaskValidator;
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
@RequestMapping("/task")
public class TaskController {
    private final TaskManager taskManager;
    private final TasksShower tasksShower;
    private final TaskCompleter taskCompleter;
    private final TaskDelegator taskDelegator;
    private final TaskValidator taskValidator;
    private final UserTarget userTarget;

    @Autowired
    public TaskController(TaskManager taskManager, TasksShower tasksShower, TaskCompleter taskCompleter, TaskDelegator taskDelegator, TaskValidator taskValidator, UserTarget userTarget) {
        this.taskManager = taskManager;
        this.tasksShower = tasksShower;
        this.taskCompleter = taskCompleter;
        this.taskDelegator = taskDelegator;
        this.taskValidator = taskValidator;
        this.userTarget = userTarget;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createTask(@RequestBody Task task, BindingResult bindingResult) {
        taskValidator.validate(task, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.internalServerError().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        taskManager.create(task);
        return ResponseEntity.created(URI.create("")).body(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateTask(@RequestBody Task task,
                                                 @PathVariable("id") int id,
                                                 BindingResult bindingResult) {
        taskValidator.validate(task, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.internalServerError().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        taskManager.update(id, task);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable("id") int id) {
        try {
            taskManager.delete(id);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public List<Task> getTasks(@PathVariable("id") int id) {
        try {
            return tasksShower.getTasks(id);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/project/{id}")
    @ResponseBody
    public List<Task> getProjectTasks(@PathVariable("id") int id) {
        try {
            return tasksShower.getTasksByProject(id);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/unsigned/{id}")
    @ResponseBody
    public List<Task> getTasksUnsigned(@PathVariable("id") int id) {
        try {
            return tasksShower.getTasksUnsigned(id);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/inwork/{id}")
    @ResponseBody
    public List<Task> getTasksInWork(@PathVariable("id") int id) {
        try {
            return tasksShower.getTasksInWork(id);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/history/{id}")
    @ResponseBody
    public List<Task> getTaskHistory(@PathVariable("id") int id) {
        try {
            return tasksShower.getTaskHistory(id);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/my/{id}")
    @ResponseBody
    public List<Task> getMyTasks(@PathVariable("id") int id) {
        try {
            return tasksShower.getMyTasks(id, userTarget.getTokenUser().getId());
        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping("/complete/{id}")
    public ResponseEntity<HttpStatus> completeTask(@PathVariable("id") int id) {
        try {
            taskCompleter.complete(id);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @PutMapping("/fail/{id}")
    public ResponseEntity<HttpStatus> failTask(@PathVariable("id") int id) {
        try {
            taskCompleter.fail(id);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @PutMapping("/set/{id}")
    public ResponseEntity<HttpStatus> setTask(@PathVariable("id") int id,
                                              @RequestBody Account account) {
        try {
            taskDelegator.set(id, account.getId());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @PutMapping("/reset/{id}")
    public ResponseEntity<HttpStatus> resetTask(@PathVariable("id") int id,
                                                @RequestBody Account account) {
        try {
            taskDelegator.reset(id, account.getId());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @GetMapping("/category")
    @ResponseBody
    public List<TaskCategory> getCategories() {
        try {
            return tasksShower.getCategories();
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/type")
    @ResponseBody
    public List<TaskType> getTypes() {
        try {
            return tasksShower.getTypes();
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/status")
    @ResponseBody
    public List<TaskStatus> getStatuses() {
        try {
            return tasksShower.getStatuses();
        } catch (Exception e) {
            return null;
        }
    }
}
