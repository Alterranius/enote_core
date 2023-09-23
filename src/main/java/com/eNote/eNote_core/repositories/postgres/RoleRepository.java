package com.eNote.eNote_core.repositories.postgres;

import com.eNote.eNote_core.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Alterranius
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findRoleByProject_IdAndName(int project_id, String name);
    List<Role> findRolesByProject_Id(int project_id);
    List<Role> findRolesByDepart_Id(int depart_id);
    List<Role> findRolesByDepart_Project_Id(int project_id);
    List<Role> findRolesByTeam_Depart_Project_Id(int project_id);
    List<Role> findRolesByTeam_Id(int team_id);
}
