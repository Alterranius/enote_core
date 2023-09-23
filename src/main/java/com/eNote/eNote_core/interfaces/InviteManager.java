package com.eNote.eNote_core.interfaces;

import com.eNote.eNote_core.models.Invite;

/**
 * @author Alterranius
 */
public interface InviteManager {
    String acceptInvite(String id);
    String declineInvite(String id);
    String create(String project_id, String account_id);
    String delete(String id);
}
