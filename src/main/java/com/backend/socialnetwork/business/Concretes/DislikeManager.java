package com.backend.socialnetwork.business.Concretes;

import com.backend.socialnetwork.business.Abstract.DislikeService;
import com.backend.socialnetwork.dtos.DislikeCreateCommentDTO;
import com.backend.socialnetwork.dtos.DislikeCreatePostDTO;
import com.backend.socialnetwork.dtos.DislikeViewDTO;
import com.backend.socialnetwork.entities.*;
import com.backend.socialnetwork.exceptions.ApiRequestException;
import com.backend.socialnetwork.repositories.CommentRepository;
import com.backend.socialnetwork.repositories.DislikeRepository;
import com.backend.socialnetwork.repositories.PostRepository;
import com.backend.socialnetwork.repositories.UserRepository;
import com.backend.socialnetwork.responses.DislikeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DislikeManager implements DislikeService {

    private final DislikeRepository dislikeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Override
    public DislikeViewDTO createDislikePost(DislikeCreatePostDTO dislikeCreatePostDTO) {
        User user=userRepository.findById(dislikeCreatePostDTO.getUserId()).get();
        Post post=postRepository.findById(dislikeCreatePostDTO.getPostId()).get();
        Dislike dislike=dislikeRepository.save(new Dislike(dislikeCreatePostDTO.getId(),user,post));
        return DislikeViewDTO.of(dislike);
    }

    @Override
    public void delete(Long id) {
        Dislike dislike=dislikeRepository.findById(id).orElseThrow(()->new ApiRequestException("Kaldırılamadı"));
        dislikeRepository.deleteById(dislike.getId());
    }

    @Override
    public List<DislikeResponse> getAllDislikesWithParam(Optional<Long> userId, Optional<Long> postId) {
        List<Dislike> list;
        if(userId.isPresent() && postId.isPresent()){
            list= dislikeRepository.findByUserIdAndPostId(userId.get(),postId.get());
        }
        else if(userId.isPresent()){
            list= dislikeRepository.findByUserId(userId.get());
        }
        else if(postId.isPresent()){
            list= dislikeRepository.findByPostId(postId.get());
        }else
            list= dislikeRepository.findAll();
        return list.stream().map(dislike->new DislikeResponse(dislike)).collect(Collectors.toList());
    }

    @Override
    public DislikeViewDTO createDislikeComment(DislikeCreateCommentDTO dislikeCreateCommentDTO) {
        User user=userRepository.findById(dislikeCreateCommentDTO.getUserId()).get();
        Comment comment=commentRepository.findById(dislikeCreateCommentDTO.getCommentId()).get();
        Dislike dislike=dislikeRepository.save(new Dislike(dislikeCreateCommentDTO.getId(),user,comment));
        return DislikeViewDTO.of(dislike);
    }
}
