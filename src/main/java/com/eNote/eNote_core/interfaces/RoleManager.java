package com.eNote.eNote_core.interfaces;

import com.eNote.eNote_core.models.Account;
import com.eNote.eNote_core.models.Project;
import com.eNote.eNote_core.models.Role;
import com.eNote.eNote_core.models.Unit;

/**
 * @author Alterranius
 */
public interface RoleManager {
    String set(Role role, int account_id);
    String reset(Role role, int account_id);
}
