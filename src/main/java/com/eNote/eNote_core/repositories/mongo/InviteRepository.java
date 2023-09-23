package com.eNote.eNote_core.repositories.mongo;

import com.eNote.eNote_core.models.Invite;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author Alterranius
 */
public interface InviteRepository extends MongoRepository<Invite, String> {
    List<Invite> findInvitesByAccount(String id);
    List<Invite> findInvitesByProject(String id);
}
