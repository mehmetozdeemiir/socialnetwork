package com.backend.socialnetwork.dtos;

import com.backend.socialnetwork.entities.Comment;
import lombok.Data;

@Data
public final class CommentViewDTO {
    private final Long id;
    private final String text;

    private CommentViewDTO(Long id,String text ) {
        this.id=id;
        this.text = text;
    }
    public static CommentViewDTO of(Comment comment){
        return new CommentViewDTO(comment.getId(), comment.getText());
    }
}
