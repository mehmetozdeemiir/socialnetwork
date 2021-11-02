package com.backend.socialnetwork.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class CommentUpdateDTO {

    private Long id;

    @NotEmpty
    @Size(min = 3, max = 250)
    private String text;
}
