package com.backend.socialnetwork.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LikeCreatePostDTO {
    private Long id;
    private Long userId;
    private Long postId;
}
