package com.eNote.eNote_core.interfaces;

import com.eNote.eNote_core.models.Account;
import com.eNote.eNote_core.models.Task;

/**
 * @author Alterranius
 */
public interface TaskDelegator {
    String set(int task_id, int account_id);
    String reset(int task_id, int account_id);
}
