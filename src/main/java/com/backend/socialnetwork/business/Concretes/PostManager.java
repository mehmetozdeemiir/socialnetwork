package com.backend.socialnetwork.business.Concretes;
import com.backend.socialnetwork.business.Abstract.CommentService;
import com.backend.socialnetwork.business.Abstract.DislikeService;
import com.backend.socialnetwork.business.Abstract.LikeService;
import com.backend.socialnetwork.business.Abstract.PostService;
import com.backend.socialnetwork.dtos.PostCreateDTO;
import com.backend.socialnetwork.dtos.PostUpdateDTO;
import com.backend.socialnetwork.dtos.PostViewDTO;
import com.backend.socialnetwork.entities.Post;
import com.backend.socialnetwork.entities.User;
import com.backend.socialnetwork.exceptions.ApiRequestException;
import com.backend.socialnetwork.repositories.DislikeRepository;
import com.backend.socialnetwork.repositories.PostRepository;
import com.backend.socialnetwork.repositories.UserRepository;
import com.backend.socialnetwork.responses.CommentResponse;
import com.backend.socialnetwork.responses.DislikeResponse;
import com.backend.socialnetwork.responses.LikeResponse;
import com.backend.socialnetwork.responses.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostManager implements PostService
{
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeService likeService;
    private final CommentService commentService;
    private final DislikeService dislikeService;

    @Override
    public PostViewDTO createPost(PostCreateDTO postCreateDTO) {
        User user= userRepository.findById(postCreateDTO.getUserId()).get();
        Post post=postRepository.save(new Post(postCreateDTO.getId(), postCreateDTO.getTitle(),postCreateDTO.getText(),user));
        return PostViewDTO.of(post);
    }

    @Override
    public PostViewDTO updatePost(Long id, PostUpdateDTO postUpdateDTO) {
        Post post= postRepository.findById(id).orElseThrow(()-> new ApiRequestException("Güncellenemedi"));
        post.setId(postUpdateDTO.getId());
        post.setTitle(postUpdateDTO.getTitle());
        post.setText(postUpdateDTO.getText());
        Post updatedPost=postRepository.save(post);
        return PostViewDTO.of(updatedPost);
    }

    @Override
    public void deletePost(Long id) {
        Post post= postRepository.findById(id).orElseThrow(()-> new ApiRequestException("Silinemedi"));
        postRepository.deleteById(post.getId());
    }

    //repoya query yazarak postId si su olanların likelarını getir yapabilridim ama baska secenek deniyorum db ye yaptırmıyorum aslında db yapsa daha hızlı olur
    //optional bir parametre geledebilir gelmeyedebilir. geldiği durumda user ıd ye gore postları getir gelmediği durumda tüm postları getir.
    @Override
    public List<PostResponse> getALl(Optional<Long> userId) {
        List<Post> list;
        if (userId.isPresent()){ //userId geldiyse
           list= postRepository.findByUserId(userId.get());
        }else
            list = postRepository.findAll();
        return list.stream().map(p-> {
            List<LikeResponse> likes=likeService.getAllLikesWithParam(Optional.ofNullable(null),Optional.of(p.getId()));
            List<CommentResponse> comments=commentService.getAllCommentsWithParam(Optional.ofNullable(null),Optional.of(p.getId()));
            List<DislikeResponse> dislikes=dislikeService.getAllDislikesWithParam(Optional.ofNullable(null),Optional.of(p.getId()));
            return new PostResponse(p,likes,comments,dislikes);}).collect(Collectors.toList());
    }

    @Override
    public PostViewDTO getPostById(Long id) {
        Post post= postRepository.findById(id).orElseThrow(()-> new ApiRequestException("bulunamadı"));
        return PostViewDTO.of(post);
    }

    @Override
    public List<PostViewDTO> slice(Pageable pageable) {
        return postRepository.findAll(pageable).stream().map(PostViewDTO::of).collect(Collectors.toList());
    }
}