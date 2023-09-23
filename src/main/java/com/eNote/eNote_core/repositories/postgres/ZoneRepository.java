package com.eNote.eNote_core.repositories.postgres;

import com.eNote.eNote_core.models.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Alterranius
 */
@Repository
public interface ZoneRepository extends JpaRepository<Zone, Integer> {

}
