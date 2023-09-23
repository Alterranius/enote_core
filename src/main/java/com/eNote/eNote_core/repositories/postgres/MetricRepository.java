package com.eNote.eNote_core.repositories.postgres;

import com.eNote.eNote_core.models.Metric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Alterranius
 */
@Repository
public interface MetricRepository extends JpaRepository<Metric, Integer> {

}
