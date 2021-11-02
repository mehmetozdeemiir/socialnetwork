package com.backend.socialnetwork.repositories;

import com.backend.socialnetwork.entities.Like;
import com.backend.socialnetwork.responses.LikeResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {

    List<Like> findByUserId(Long userId);
    List<Like> findByPostId(Long postId);
    List<Like> findByUserIdAndPostId(Long userId, Long postId);


}
