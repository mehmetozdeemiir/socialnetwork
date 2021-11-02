package com.backend.socialnetwork.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DislikeCreateCommentDTO {
    private Long id;
    private Long userId;
    private Long commentId;
}
