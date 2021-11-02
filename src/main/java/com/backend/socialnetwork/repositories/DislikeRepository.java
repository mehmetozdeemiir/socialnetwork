package com.backend.socialnetwork.repositories;

import com.backend.socialnetwork.entities.Dislike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DislikeRepository extends JpaRepository<Dislike,Long> {
    List<Dislike> findByUserIdAndPostId(Long userId, Long postId);

    List<Dislike> findByUserId(Long userId);

    List<Dislike> findByPostId(Long postId);
}
