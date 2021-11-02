package com.backend.socialnetwork.dtos;
import com.backend.socialnetwork.entities.Post;
import lombok.Data;

@Data
public final class PostViewDTO
{
    private final Long id;
    private final String title;
    private final String text;

    private PostViewDTO(Long id,String title, String text ) {
        this.id=id;
        this.title = title;
        this.text = text;
    }
    public static PostViewDTO of(Post post){
        return new PostViewDTO(post.getId(),post.getTitle(), post.getText());
    }
}
