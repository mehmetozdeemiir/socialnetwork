package com.backend.socialnetwork.business.Abstract;

import com.backend.socialnetwork.dtos.PostCreateDTO;
import com.backend.socialnetwork.dtos.PostUpdateDTO;
import com.backend.socialnetwork.dtos.PostViewDTO;
import com.backend.socialnetwork.responses.PostResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PostService {
    PostViewDTO createPost(PostCreateDTO postCreateDTO);
    PostViewDTO updatePost(Long id, PostUpdateDTO postUpdateDTO);
    void deletePost(Long id);
    List<PostResponse> getALl(Optional<Long> userId);
    PostViewDTO getPostById(Long id);
    List<PostViewDTO> slice(Pageable pageable);
}
