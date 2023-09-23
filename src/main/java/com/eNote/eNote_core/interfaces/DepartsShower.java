package com.eNote.eNote_core.interfaces;

import com.eNote.eNote_core.dto.DepartDataDTO;
import com.eNote.eNote_core.models.Depart;

import java.util.List;

/**
 * @author Alterranius
 */
public interface DepartsShower {
    List<Depart> getDeparts(int id);
    DepartDataDTO getTasksStats(int id);
}
