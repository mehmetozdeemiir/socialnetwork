package com.backend.socialnetwork.business.Concretes;

import com.backend.socialnetwork.business.Abstract.CommentService;
import com.backend.socialnetwork.business.Abstract.DislikeService;
import com.backend.socialnetwork.business.Abstract.LikeService;
import com.backend.socialnetwork.dtos.CommentCreateDTO;
import com.backend.socialnetwork.dtos.CommentUpdateDTO;
import com.backend.socialnetwork.dtos.CommentViewDTO;
import com.backend.socialnetwork.entities.Comment;
import com.backend.socialnetwork.entities.Post;
import com.backend.socialnetwork.entities.User;
import com.backend.socialnetwork.exceptions.ApiRequestException;
import com.backend.socialnetwork.repositories.CommentRepository;
import com.backend.socialnetwork.repositories.PostRepository;
import com.backend.socialnetwork.repositories.UserRepository;
import com.backend.socialnetwork.responses.CommentResponse;
import com.backend.socialnetwork.responses.DislikeResponse;
import com.backend.socialnetwork.responses.LikeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentManager implements CommentService
{
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final LikeService likeService;
    private final DislikeService dislikeService;

    @Override
    public CommentViewDTO getCommentById(Long id) {
        final Comment comment= this.commentRepository.findById(id).orElseThrow(() -> new ApiRequestException("bulunamadı"));//getById de calısmıyor.
        return CommentViewDTO.of(comment);
    }

    @Override
    public CommentViewDTO createComment(CommentCreateDTO commentCreateDTO) {
        User user= userRepository.findById(commentCreateDTO.getUserId()).get();
        Post post= postRepository.findById(commentCreateDTO.getPostId()).get();
        Comment comment = commentRepository.save(new Comment(commentCreateDTO.getId(),commentCreateDTO.getText(),user,post));
        if(comment==null){
            throw new ApiRequestException("yorum eklenemedi.");
        }
        return CommentViewDTO.of(comment);
    }

    @Override
    public CommentViewDTO updateComment(Long id, CommentUpdateDTO commentUpdateDTO) {
        final Comment comment= this.commentRepository.findById(id).orElseThrow(() -> new ApiRequestException("bulunamadı"));
        comment.setId(comment.getId());
        comment.setText(commentUpdateDTO.getText());
        commentRepository.save(comment);
        return CommentViewDTO.of(comment);
    }

    @Override
    public void deleteComment(Long id) {
        Comment comment = this.commentRepository.findById(id).orElseThrow(()->new ApiRequestException("kaldırılamadı"));
        this.commentRepository.deleteById(comment.getId());
    }
    //ayrı ayrı gelme durumları
    @Override
    public List<CommentResponse> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {
        List<Comment> list;
        if(userId.isPresent() && postId.isPresent()){
            list= commentRepository.findByUserIdAndPostId(userId.get(),postId.get());
        }
        else if(userId.isPresent()){
            list= commentRepository.findByUserId(userId.get());
        }
        else if(postId.isPresent()){
            list= commentRepository.findByPostId(postId.get());
        }else
        list= commentRepository.findAll();
        return list.stream().map(comment ->{
            List<LikeResponse> likes=likeService.getAllLikesWithParam(Optional.ofNullable(null),Optional.of(comment.getId()));
            List<DislikeResponse> dislikes=dislikeService.getAllDislikesWithParam(Optional.ofNullable(null),Optional.of(comment.getId()));
            return new CommentResponse(comment,likes,dislikes);}).collect(Collectors.toList());


    }
}
