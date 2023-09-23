package com.eNote.eNote_core.interfaces;

import com.eNote.eNote_core.models.Task;
import com.eNote.eNote_core.models.TaskStatus;

/**
 * @author Alterranius
 */
public interface TaskCompleter {
    String setStatus(Task task, TaskStatus status);
    String complete(int id);
    String fail(int id);
}
