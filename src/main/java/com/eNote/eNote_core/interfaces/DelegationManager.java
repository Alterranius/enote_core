package com.eNote.eNote_core.interfaces;

import com.eNote.eNote_core.models.Delegation;

/**
 * @author Alterranius
 */
public interface DelegationManager {
    String create(Delegation delegation, String account_id);
    String update(String id, Delegation delegation);
    String delete(String id);
    String complete(String id);
}
