package com.backend.socialnetwork.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
public class PostUpdateDTO {

    private Long id;

    @Size(min = 3, max = 250)
    @NotEmpty(message = "title cannot be null")
    private String title;

    @Size(min = 3, max = 450)
    @NotEmpty(message = "text cannot be null")
    private String text;

}
