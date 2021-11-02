package com.backend.socialnetwork.business.Abstract;

import com.backend.socialnetwork.dtos.CommentCreateDTO;
import com.backend.socialnetwork.dtos.CommentUpdateDTO;
import com.backend.socialnetwork.dtos.CommentViewDTO;
import com.backend.socialnetwork.responses.CommentResponse;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    CommentViewDTO getCommentById(Long id);
    CommentViewDTO createComment(CommentCreateDTO commentCreateDTO);
    CommentViewDTO updateComment(Long id, CommentUpdateDTO commentUpdateDTO);
    void deleteComment(Long id);
    List<CommentResponse> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId);
}
