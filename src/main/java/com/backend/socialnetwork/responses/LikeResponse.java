package com.backend.socialnetwork.responses;

import com.backend.socialnetwork.entities.Like;
import lombok.Data;

@Data
public class LikeResponse {
       private Long id;
       private Long userId;
       private Long postId;
       private Long commentId;

    public LikeResponse(Like like) {
        this.id = like.getId();
        this.userId = like.getUser().getId();
        this.postId = like.getPost().getId();
        this.commentId=like.getComment().getId();
    }
}
