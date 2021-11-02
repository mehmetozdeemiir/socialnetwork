package com.backend.socialnetwork.controllers;

import com.backend.socialnetwork.business.Abstract.CommentService;
import com.backend.socialnetwork.dtos.CommentCreateDTO;
import com.backend.socialnetwork.dtos.CommentUpdateDTO;
import com.backend.socialnetwork.dtos.CommentViewDTO;
import com.backend.socialnetwork.entities.Comment;
import com.backend.socialnetwork.responses.CommentResponse;
import com.backend.socialnetwork.validations.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/comments")
    public List<CommentResponse> getAll(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId){
        return commentService.getAllCommentsWithParam(userId,postId);
    }

    @GetMapping("/comments/{id}")
    public CommentViewDTO getByCommentId(@PathVariable Long id){
        return this.commentService.getCommentById(id);
    }

    @PostMapping("/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentViewDTO createComment(@RequestBody CommentCreateDTO commentCreateDTO){
        return this.commentService.createComment(commentCreateDTO);
    }

    @PutMapping("/comments/{id}")
    public CommentViewDTO updateComment(@PathVariable Long id, @RequestBody CommentUpdateDTO commentUpdateDTO){
        return commentService.updateComment(id,commentUpdateDTO);
    }

    @DeleteMapping("/comments/{id}")
    public void deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);
    }
}