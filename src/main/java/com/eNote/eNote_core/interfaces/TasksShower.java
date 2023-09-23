package com.eNote.eNote_core.interfaces;

import com.eNote.eNote_core.models.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Alterranius
 */
public interface TasksShower {
    List<Task> getTasks(int id);
    List<Task> getTasksByProject(int id);
    List<Task> getTasksUnsigned(int id);
    List<Task> getTasksInWork(int id);
    List<Task> getMyTasks(int project_id, int account_id);
    List<Task> getTaskHistory(int id);
    Optional<Task> getTaskDetails(int id);
    List<TaskCategory> getCategories();
    List<TaskType> getTypes();
    List<TaskStatus> getStatuses();

}
