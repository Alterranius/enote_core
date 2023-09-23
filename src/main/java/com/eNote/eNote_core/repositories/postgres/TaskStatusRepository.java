package com.eNote.eNote_core.repositories.postgres;

import com.eNote.eNote_core.models.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Alterranius
 */
@Repository
public interface TaskStatusRepository extends JpaRepository<TaskStatus, Integer> {
    Optional<TaskStatus> findTaskStatusByName(String name);
}
