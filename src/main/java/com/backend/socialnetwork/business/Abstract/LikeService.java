package com.backend.socialnetwork.business.Abstract;

import com.backend.socialnetwork.dtos.LikeCreateCommentDTO;
import com.backend.socialnetwork.dtos.LikeCreatePostDTO;
import com.backend.socialnetwork.dtos.LikeViewDTO;
import com.backend.socialnetwork.responses.LikeResponse;
import java.util.List;
import java.util.Optional;

public interface LikeService {

    LikeViewDTO createLikePost(LikeCreatePostDTO likeCreatePostDTO);

    void delete(Long id);

    List<LikeResponse> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId);

    LikeViewDTO createLikeComment(LikeCreateCommentDTO likeCreateCommentDTO);

}
