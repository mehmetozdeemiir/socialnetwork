package com.backend.socialnetwork.controllers;

import com.backend.socialnetwork.business.Abstract.DislikeService;
import com.backend.socialnetwork.dtos.DislikeCreateCommentDTO;
import com.backend.socialnetwork.dtos.DislikeCreatePostDTO;
import com.backend.socialnetwork.dtos.DislikeViewDTO;
import com.backend.socialnetwork.responses.DislikeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DislikeController {

    private final DislikeService dislikeService;

    @PostMapping("/dislikes/post")
    @ResponseStatus(HttpStatus.CREATED)
    public DislikeViewDTO dislikeCreatePost(@RequestBody DislikeCreatePostDTO dislikeCreatePostDTO){
        return dislikeService.createDislikePost(dislikeCreatePostDTO);
    }

    @PostMapping("/dislikes/comment")
    @ResponseStatus(HttpStatus.CREATED)
    public DislikeViewDTO dislikeCreateComment(@RequestBody DislikeCreateCommentDTO dislikeCreateCommentDTO){
        return dislikeService.createDislikeComment(dislikeCreateCommentDTO);
    }

    @DeleteMapping("/dislikes/{id}")
    public void deleteDislike(@PathVariable Long id){
        dislikeService.delete(id);
    }

    @GetMapping("/dislikes")
    public List<DislikeResponse> getAllWithParams(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId) {
        return dislikeService.getAllDislikesWithParam(userId,postId);
    }

}

