package com.eNote.eNote_core.interfaces;

import com.eNote.eNote_core.models.Account;
import com.eNote.eNote_core.models.Unit;

import java.util.List;

/**
 * @author Alterranius
 */
public interface MembersShower {
    List<Account> getMembers(Unit unit);
}
