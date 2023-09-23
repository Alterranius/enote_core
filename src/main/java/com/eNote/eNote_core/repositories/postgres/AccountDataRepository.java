package com.eNote.eNote_core.repositories.postgres;

import com.eNote.eNote_core.models.Account;
import com.eNote.eNote_core.models.AccountData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Alterranius
 */
@Repository
public interface AccountDataRepository extends JpaRepository<AccountData, Account> {
    Optional<AccountData> findByUsername(String username);
}
