package com.backend.socialnetwork.dtos;

import com.backend.socialnetwork.entities.Like;
import lombok.Data;

@Data
public final class LikeViewDTO {
    private final Long id;

    private LikeViewDTO(Long id) {
        this.id = id;
    }
    public static LikeViewDTO of(Like like){
        return new LikeViewDTO(like.getId());
    }

}
