package com.eNote.eNote_core.services.comment.util;

import com.eNote.eNote_core.models.Delegation;
import com.eNote.eNote_core.services.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Alterranius
 */
@Component
public class CommentValidator implements Validator {
    private final CommentService commentService;

    @Autowired
    public CommentValidator(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Delegation.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
