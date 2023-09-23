package com.eNote.eNote_core.repositories.mongo;

import com.eNote.eNote_core.models.Appeal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alterranius
 */
@Repository
public interface AppealRepository extends MongoRepository<Appeal, String> {
    List<Appeal> findAppealsBySender(String account_id);
    List<Appeal> findAppealsByReceiver(String account_id);
    List<Appeal> findAppealsByReceiverOrSender(String receiver, String sender);

}
