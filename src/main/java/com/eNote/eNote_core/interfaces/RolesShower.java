package com.eNote.eNote_core.interfaces;

import com.eNote.eNote_core.dto.UnitAccountsDTO;
import com.eNote.eNote_core.dto.UnitStatsDTO;
import com.eNote.eNote_core.models.Account;
import com.eNote.eNote_core.models.Role;
import com.eNote.eNote_core.models.Unit;

import java.util.List;

/**
 * @author Alterranius
 */
public interface RolesShower {
    List<Role> getProjectRoles(int project_id);
    List<Role> getDepartRoles(int depart_id);
    List<Role> getTeamRoles(int team_id);
    List<UnitAccountsDTO> getProjectAccounts(int id);
    List<UnitAccountsDTO> getDepartAccounts(int id);
    List<UnitAccountsDTO> getTeamAccounts(int id);
}
