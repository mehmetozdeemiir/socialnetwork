package com.backend.socialnetwork.dtos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class CommentCreateDTO {
    private Long id;

    @NotEmpty
    @Size(min = 3, max = 250)
    private String text;

    private Long userId;

    private Long postId;
}
