package com.eNote.eNote_core.interfaces;


import com.eNote.eNote_core.models.Task;

/**
 * @author Alterranius
 */
public interface TaskManager {
    String create(Task task);
    String update(int id, Task task);
    String delete(int id);
}
