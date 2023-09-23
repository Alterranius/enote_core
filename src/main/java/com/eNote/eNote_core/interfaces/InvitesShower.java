package com.eNote.eNote_core.interfaces;

import com.eNote.eNote_core.models.Account;
import com.eNote.eNote_core.models.Invite;
import com.eNote.eNote_core.models.Project;

import java.util.List;

/**
 * @author Alterranius
 */
public interface InvitesShower {
    List<Invite> getInvitesByAccount(int account_id);
    List<Invite> getInvitesByProject(int project_id);
}
