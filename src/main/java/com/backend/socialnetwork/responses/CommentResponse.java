package com.backend.socialnetwork.responses;

import com.backend.socialnetwork.entities.Comment;
import lombok.Data;

import java.util.List;

@Data
public class CommentResponse {
    private Long id;
    private String text;
    private Long userId;
    private String userName;
    private List<LikeResponse> commentLikes;
    private List<DislikeResponse> commentDislike;

    public CommentResponse(Comment comment,List<LikeResponse> commentLikes , List<DislikeResponse> commentDislike){
        this.id=comment.getId();
        this.text= comment.getText();
        this.userId =comment.getUser().getId();
        this.userName=comment.getUser().getUserName();
        this.commentLikes=commentLikes;
        this.commentDislike=commentDislike;

    }

}
