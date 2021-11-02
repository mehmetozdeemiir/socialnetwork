package com.backend.socialnetwork.responses;

import com.backend.socialnetwork.entities.Dislike;
import lombok.Data;

@Data
public class DislikeResponse {
    private Long id;
    private Long userId;
    private Long postId;
    private Long commentId;

    public DislikeResponse(Dislike dislike) {
        this.id = dislike.getId();
        this.userId = dislike.getUser().getId();
        this.postId = dislike.getPost().getId();
        this.commentId=dislike.getComment().getId();
    }
}
