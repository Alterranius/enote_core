package com.eNote.eNote_core.repositories.postgres;

import com.eNote.eNote_core.models.Depart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alterranius
 */
@Repository
public interface DepartRepository extends JpaRepository<Depart, Integer> {
    List<Depart> findDepartsByProject_Id(int project_id);
}
