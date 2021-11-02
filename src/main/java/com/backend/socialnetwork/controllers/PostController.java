package com.backend.socialnetwork.controllers;
import com.backend.socialnetwork.business.Abstract.PostService;
import com.backend.socialnetwork.dtos.PostCreateDTO;
import com.backend.socialnetwork.dtos.PostUpdateDTO;
import com.backend.socialnetwork.dtos.PostViewDTO;
import com.backend.socialnetwork.responses.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/posts/getall")
    public List<PostResponse> getAll(@RequestParam Optional<Long> userId){
        return postService.getALl(userId);
    }

    @GetMapping("/posts/{id}")
    public PostViewDTO getPostById(@PathVariable Long id){
        PostViewDTO post=this.postService.getPostById(id);
        return post;
    }

    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public PostViewDTO createPost(@Valid @RequestBody PostCreateDTO postCreateDTO){
       return postService.createPost(postCreateDTO);
    }

    @PutMapping("/posts/{id}")
    public PostViewDTO updatePost(@PathVariable Long id, @Valid @RequestBody PostUpdateDTO postUpdateDTO){
        return postService.updatePost(id,postUpdateDTO);
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable Long id){
        postService.deletePost(id);
    }

    @GetMapping("/posts/slice")
    public List<PostViewDTO> slice(Pageable pageable){
        List<PostViewDTO> posts = postService.slice(pageable);
        return posts;
    }
}

