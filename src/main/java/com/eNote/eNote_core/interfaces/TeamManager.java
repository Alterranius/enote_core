package com.eNote.eNote_core.interfaces;

import com.eNote.eNote_core.models.Team;

/**
 * @author Alterranius
 */
public interface TeamManager {
    String create(Team team);
    String delete(int id);
    String update(int id, Team team);
}
