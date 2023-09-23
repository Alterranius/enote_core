package com.eNote.eNote_core.interfaces;

import com.eNote.eNote_core.dto.TeamDataDTO;
import com.eNote.eNote_core.models.Team;

import java.util.List;

/**
 * @author Alterranius
 */
public interface TeamsShower {
    List<Team> getTeams(int id);
    List<Team> getProjectTeams(int id);
    TeamDataDTO getTasksStats(int id);
}
