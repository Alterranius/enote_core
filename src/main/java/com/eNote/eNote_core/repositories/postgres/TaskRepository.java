package com.eNote.eNote_core.repositories.postgres;

import com.eNote.eNote_core.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alterranius
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findTasksByTeam_Depart_Project_Id(int project_id);
    List<Task> findTasksByTeam_Depart_Project_IdAndAccount_Id(int project_id, int account_id);
    List<Task> findTasksByTeam_Depart_Project_IdAndStatus_Id(int project_id, int status_id);
    List<Task> findTasksByTeam_Id(int team_id);
    List<Task> findTasksByTeam_Depart_Id(int depart_id);
    List<Task> findTasksByAccount_Id(int account_id);
}
