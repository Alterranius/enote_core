package com.eNote.eNote_core.repositories.postgres;

import com.eNote.eNote_core.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alterranius
 */
@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
    List<Team> findTeamsByDepart_Id(int depart_id);
    List<Team> findTeamsByDepart_Project_Id(int project_Id);
}
