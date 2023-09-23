package com.eNote.eNote_core.repositories.mongo;

import com.eNote.eNote_core.models.Delegation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alterranius
 */
@Repository
public interface DelegationRepository extends MongoRepository<Delegation, String> {
    List<Delegation> findDelegationsBySender(String account_id);
    List<Delegation> findDelegationsByReceiver(String account_id);
    List<Delegation> findDelegationsByReceiverOrSender(String receiver, String sender);
    List<Delegation> findDelegationsByTask(String task_id);
}
