package com.eNote.eNote_core.repositories.mongo;

import com.eNote.eNote_core.models.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alterranius
 */
@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findCommentsByTask(String task_id);
}
