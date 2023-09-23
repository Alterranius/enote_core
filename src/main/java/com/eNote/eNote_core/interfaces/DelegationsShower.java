package com.eNote.eNote_core.interfaces;

import com.eNote.eNote_core.models.Account;
import com.eNote.eNote_core.models.Delegation;
import com.eNote.eNote_core.models.Task;
import com.eNote.eNote_core.models.Unit;

import java.util.List;
import java.util.Optional;

/**
 * @author Alterranius
 */
public interface DelegationsShower {
    List<Delegation> getDelegations(String project_id);
    Optional<Delegation> getDelegationDetails(Delegation delegation);
    List<Delegation> getDelegationsByTask(Task task);
    List<Delegation> getDelegationsBySender(Account account);
    List<Delegation> getDelegationsByReceiver(Account account);
}
