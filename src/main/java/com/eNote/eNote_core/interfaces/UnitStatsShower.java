package com.eNote.eNote_core.interfaces;

import com.eNote.eNote_core.dto.AccountStatsDTO;
import com.eNote.eNote_core.dto.UnitStatsDTO;

import java.util.Optional;

/**
 * @author Alterranius
 */
public interface UnitStatsShower {
    Optional<UnitStatsDTO> getTeamStats(int id);
    Optional<UnitStatsDTO> getDepartStats(int id);
    Optional<UnitStatsDTO> getProjectStats(int id);
    Optional<AccountStatsDTO> getAccountStats(int id);
}
