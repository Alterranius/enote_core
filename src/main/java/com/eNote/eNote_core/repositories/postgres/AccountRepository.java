package com.eNote.eNote_core.repositories.postgres;

import com.eNote.eNote_core.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Alterranius
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    List<Account> findByRoles_Project_Id(int project_id);

    List<Account> findByRoles_Team_Id(int team_id);

    List<Account> findByRoles_Depart_Id(int depart_id);

    Optional<Account> findAccountByAccountData_Username(String username);
}
