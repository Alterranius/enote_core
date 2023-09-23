package com.eNote.eNote_core.interfaces;

import com.eNote.eNote_core.models.Project;

/**
 * @author Alterranius
 */
public interface ProjectManager {
    String create(Project project, int account_id);
    String update(int id, Project project);
    String delete(int id);
    String leave(int project_id, int account_id);
}
