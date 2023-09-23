package com.eNote.eNote_core.interfaces;

import com.eNote.eNote_core.models.Comment;
import com.eNote.eNote_core.models.Task;

/**
 * @author Alterranius
 */
public interface CommentManager {
    String create(Comment comment);
    String delete(String id);
}
