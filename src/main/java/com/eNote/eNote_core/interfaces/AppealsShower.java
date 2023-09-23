package com.eNote.eNote_core.interfaces;

import com.eNote.eNote_core.models.Account;
import com.eNote.eNote_core.models.Appeal;
import com.eNote.eNote_core.models.Unit;

import java.util.List;
import java.util.Optional;

/**
 * @author Alterranius
 */
public interface AppealsShower {
    List<Appeal> getAppeals(String project_id);
    List<Appeal> getSentAppeals(Account account);
    List<Appeal> getReceivedAppeals(Account account);
    Optional<Appeal> getAppealDetails(Appeal appeal);
}
