package com.eNote.eNote_core.controllers;

import com.eNote.eNote_core.services.config.UserTarget;
import com.eNote.eNote_core.dto.CommentDTO;
import com.eNote.eNote_core.interfaces.AccountsShower;
import com.eNote.eNote_core.interfaces.CommentManager;
import com.eNote.eNote_core.interfaces.CommentsShower;
import com.eNote.eNote_core.interfaces.TasksShower;
import com.eNote.eNote_core.models.Comment;
import com.eNote.eNote_core.services.comment.util.CommentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Alterranius
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentManager commentManager;
    private final CommentsShower commentsShower;
    private final AccountsShower accountsShower;
    private final TasksShower tasksShower;
    private final CommentValidator commentValidator;
    private final UserTarget userTarget;

    @Autowired
    public CommentController(CommentManager commentManager, CommentsShower commentsShower, AccountsShower accountsShower, TasksShower tasksShower, CommentValidator commentValidator, UserTarget userTarget) {
        this.commentManager = commentManager;
        this.commentsShower = commentsShower;
        this.accountsShower = accountsShower;
        this.tasksShower = tasksShower;
        this.commentValidator = commentValidator;
        this.userTarget = userTarget;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createComment(@RequestBody CommentDTO commentDTO) {
        Comment comment = convertFromDto(commentDTO);
        comment.setAccount(String.valueOf(userTarget.getTokenUser().getId()));
        comment.setId(null);
        commentManager.create(comment);
        return ResponseEntity.created(URI.create("")).body(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable("id") String id) {
        commentManager.delete(id);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public List<CommentDTO> getComments(@PathVariable("id") String id) {
        List<Comment> comments = commentsShower.getComments(id);
        List<CommentDTO> result = new ArrayList<>();
        for (Comment c : comments) {
            try {
                result.add(convertToDto(c));
            } catch (Exception ignored) {
            }
        }
        return result;
    }

    private Comment convertFromDto(CommentDTO commentDTO) {
        return new Comment(
                commentDTO.getId(),
                commentDTO.getHead(),
                commentDTO.getContent(),
                commentDTO.getCreatedAt().toString(),
                "",
                String.valueOf(commentDTO.getTask().getId())
        );
    }
    private CommentDTO convertToDto(Comment comment) throws ParseException {
        return new CommentDTO(
                comment.getId(),
                comment.getHead(),
                comment.getContent(),
                new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy", Locale.ENGLISH).parse(comment.getCreatedAt()),
                accountsShower.getAccount(Integer.parseInt(comment.getAccount())).get(),
                tasksShower.getTaskDetails(Integer.parseInt(comment.getTask())).get()
        );
    }
}
