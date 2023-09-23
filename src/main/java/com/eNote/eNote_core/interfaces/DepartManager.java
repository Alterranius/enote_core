package com.eNote.eNote_core.interfaces;

import com.eNote.eNote_core.models.Depart;

/**
 * @author Alterranius
 */
public interface DepartManager {
    String create(Depart depart);
    String delete(int id);
    String update(int id, Depart depart);
}
