package com.eNote.eNote_core.services.comment;

import com.eNote.eNote_core.interfaces.CommentManager;
import com.eNote.eNote_core.interfaces.CommentsShower;
import com.eNote.eNote_core.models.Comment;
import com.eNote.eNote_core.repositories.mongo.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Alterranius
 */
@Service
public class CommentService implements CommentManager, CommentsShower {
    private final CommentRepository commentRepository;
    private static final Logger commentLogger = LoggerFactory.getLogger(CommentService.class);

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public String create(Comment comment) {
        comment.setCreatedAt(new Date().toString());
        commentRepository.insert(comment);
        commentLogger.debug("Comment was sent");
        return "Добавлено";
    }

    @Override
    public String delete(String id) {
        Optional<Comment> currentComment = commentRepository.findById(id);
        if (currentComment.isPresent()) {
            commentRepository.delete(currentComment.get());
            commentLogger.debug("Comment was removed");
            return "Удалено";
        } else {
            return "Неактуальный комментарий";
        }
    }

    @Override
    public List<Comment> getComments(String id) {
        return new ArrayList<>(commentRepository.findCommentsByTask(id));
    }

    @Override
    public Optional<Comment> getCommentDetails(Comment comment) {
        return commentRepository.findById(comment.getId());
    }
}
