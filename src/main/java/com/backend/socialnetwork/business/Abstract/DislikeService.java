package com.backend.socialnetwork.business.Abstract;

import com.backend.socialnetwork.dtos.*;
import com.backend.socialnetwork.responses.DislikeResponse;
import java.util.List;
import java.util.Optional;

public interface DislikeService {
    DislikeViewDTO createDislikePost(DislikeCreatePostDTO dislikeCreatePostDTO);

    void delete(Long id);

    List<DislikeResponse> getAllDislikesWithParam(Optional<Long> userId, Optional<Long> postId);

    DislikeViewDTO createDislikeComment(DislikeCreateCommentDTO dislikeCreateCommentDTO);
}
