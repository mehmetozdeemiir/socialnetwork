package com.backend.socialnetwork.dtos;

import com.backend.socialnetwork.entities.Dislike;
import lombok.Data;

@Data
public final class DislikeViewDTO {

    private final Long id;

    private DislikeViewDTO(Long id) {
        this.id = id;
    }
    public static DislikeViewDTO of(Dislike dislike){
        return new DislikeViewDTO(dislike.getId());
    }
}
