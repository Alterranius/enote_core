package com.eNote.eNote_core.interfaces;

import com.eNote.eNote_core.models.Comment;
import com.eNote.eNote_core.models.Task;

import java.util.List;
import java.util.Optional;

/**
 * @author Alterranius
 */
public interface CommentsShower {
    List<Comment> getComments(String id);
    Optional<Comment> getCommentDetails(Comment comment);
}
