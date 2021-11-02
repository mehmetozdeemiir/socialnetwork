package com.backend.socialnetwork.entities;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name="likes")
@Data
@NoArgsConstructor
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    public Like(Long id, User user, Post post){
        this.id = id;
        this.user = user;
        this.post = post;
    }

    public Like(Long id,User user, Comment comment){
        this.id=id;
        this.user=user;
        this.comment=comment;
    }

}
