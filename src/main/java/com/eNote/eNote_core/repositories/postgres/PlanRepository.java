package com.eNote.eNote_core.repositories.postgres;

import com.eNote.eNote_core.models.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alterranius
 */
@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {
    List<Plan> findPlansByProject_Id(int project_id);
    List<Plan> findPlansByDepart_Id(int depart_id);
}
