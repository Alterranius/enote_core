package com.eNote.eNote_core.interfaces;

import com.eNote.eNote_core.models.Plan;
import com.eNote.eNote_core.models.PlanContent;

/**
 * @author Alterranius
 */
public interface PlanManager {
    String create(Plan plan);
    String update(int id, Plan plan);
    String delete(int id);
    String addContent(int id, PlanContent planContent);
    String removeContent(int id);
    String updateContent(int id, PlanContent planContent);
}
