package com.eNote.eNote_core.interfaces;

import com.eNote.eNote_core.models.Appeal;

/**
 * @author Alterranius
 */
public interface AppealManager {
    String create(Appeal appeal, String account_id);
    String update(String id, Appeal appeal);
    String recall(String id);
    String answer(Appeal request, Appeal response);
}
