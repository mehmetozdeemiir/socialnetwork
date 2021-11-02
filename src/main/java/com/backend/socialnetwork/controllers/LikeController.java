package com.backend.socialnetwork.controllers;
import com.backend.socialnetwork.business.Abstract.LikeService;
import com.backend.socialnetwork.dtos.LikeCreateCommentDTO;
import com.backend.socialnetwork.dtos.LikeCreatePostDTO;
import com.backend.socialnetwork.dtos.LikeViewDTO;
import com.backend.socialnetwork.entities.Like;
import com.backend.socialnetwork.responses.LikeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/likes/post")
    public LikeViewDTO addLikePost(@RequestBody LikeCreatePostDTO likeCreatePostDTO){
        return likeService.createLikePost(likeCreatePostDTO);
    }

    @DeleteMapping("/likes/{id}")
    public void deleteLike(@PathVariable Long id){
        likeService.delete(id);
    }

    @GetMapping("/likes")
    public List<LikeResponse> getAllLikes(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId) {
        return likeService.getAllLikesWithParam(userId, postId);
    }

    @PostMapping("/likes/comment")
    @ResponseStatus(HttpStatus.CREATED)
    public LikeViewDTO addLikeComment(@RequestBody LikeCreateCommentDTO likeCreateCommentDTO){
        return likeService.createLikeComment(likeCreateCommentDTO);
    }

}
