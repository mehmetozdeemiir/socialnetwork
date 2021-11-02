package com.backend.socialnetwork.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class PostCreateDTO {
    private Long id;

    @Size(min = 3, max=250)
    @NotEmpty(message = "title cannot be null")
    private String title;

    @Size(min = 3, max = 550)
    @NotEmpty(message = "text cannot be null")
    private String text;

    private Long userId;
}
