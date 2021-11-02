package com.backend.socialnetwork.entities;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="comment")
@Data
@NoArgsConstructor
public class Comment{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToMany(mappedBy = "comment",fetch = FetchType.LAZY)
    private List<Like> likes;

    @OneToMany(mappedBy = "comment",fetch = FetchType.LAZY)
    private List<Dislike> dislikes;

    public Comment(Long id,String text,User user,Post post) {
        this.id=id;
        this.text = text;
        this.user=user;
        this.post=post;
    }
}