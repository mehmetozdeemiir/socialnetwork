package com.backend.socialnetwork.responses;

import com.backend.socialnetwork.entities.Post;
import lombok.Data;
import java.util.List;

@Data
public class PostResponse {
    Long id;
    Long userId;
    String userName;
    String text;
    String title;
    List<LikeResponse> postLike;
    List<CommentResponse> postComments;
    List<DislikeResponse> postDislikes;

    public PostResponse(Post post,List<LikeResponse> likes,List<CommentResponse> postComments,List<DislikeResponse> dislikes){
        this.id=post.getId();
        this.userId=post.getUser().getId();
        this.userName=post.getUser().getUserName();
        this.title= post.getTitle();
        this.text=post.getText();
        this.postLike=likes;
        this.postComments=postComments;
        this.postDislikes=dislikes;
    }


}
