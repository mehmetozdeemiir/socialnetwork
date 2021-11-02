package com.backend.socialnetwork.business.Concretes;
import com.backend.socialnetwork.business.Abstract.LikeService;
import com.backend.socialnetwork.dtos.LikeCreateCommentDTO;
import com.backend.socialnetwork.dtos.LikeCreatePostDTO;
import com.backend.socialnetwork.dtos.LikeViewDTO;
import com.backend.socialnetwork.entities.Comment;
import com.backend.socialnetwork.entities.Like;
import com.backend.socialnetwork.entities.Post;
import com.backend.socialnetwork.entities.User;
import com.backend.socialnetwork.exceptions.ApiRequestException;
import com.backend.socialnetwork.repositories.CommentRepository;
import com.backend.socialnetwork.repositories.LikeRepository;
import com.backend.socialnetwork.repositories.PostRepository;
import com.backend.socialnetwork.repositories.UserRepository;
import com.backend.socialnetwork.responses.LikeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeManager implements LikeService
{
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Override
    public LikeViewDTO createLikePost(LikeCreatePostDTO likeCreatePostDTO) {
        User user =userRepository.findById(likeCreatePostDTO.getUserId()).get();
        Post post= postRepository.findById(likeCreatePostDTO.getPostId()).get();
        Like like = likeRepository.save(new Like(likeCreatePostDTO.getId(),user,post));
        return LikeViewDTO.of(like);
    }

    @Override
    public void delete(Long id) {
        Like like= likeRepository.findById(id).orElseThrow(()-> new ApiRequestException("Silinemedi"));
        likeRepository.deleteById(like.getId());
    }

    @Override
    public List<LikeResponse> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId) {
        List<Like> list;
        if(userId.isPresent() && postId.isPresent()){
            list= likeRepository.findByUserIdAndPostId(userId.get(),postId.get());
        }
        else if(userId.isPresent()){
            list= likeRepository.findByUserId(userId.get());
        }
        else if(postId.isPresent()){
            list= likeRepository.findByPostId(postId.get());
        }else
            list= likeRepository.findAll();
          return list.stream().map(like->new LikeResponse(like)).collect(Collectors.toList());
    }

    @Override
    public LikeViewDTO createLikeComment(LikeCreateCommentDTO likeCreateCommentDTO) {
        User user =userRepository.findById(likeCreateCommentDTO.getUserId()).get();
        Comment comment= commentRepository.findById(likeCreateCommentDTO.getCommentId()).get();
        Like like= likeRepository.save(new Like(likeCreateCommentDTO.getId(),user,comment));
        return LikeViewDTO.of(like);
    }
}