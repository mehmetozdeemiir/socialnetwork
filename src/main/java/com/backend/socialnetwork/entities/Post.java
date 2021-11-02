package com.backend.socialnetwork.entities;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "post")
@NoArgsConstructor
//@AllArgsConstructor ihtiyacım yok
public class Post{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post",fetch = FetchType.LAZY)
    private List<Comment> comments;

    @OneToMany(mappedBy = "post",fetch = FetchType.LAZY)
    private List<Like> likes;

    @OneToMany(mappedBy = "post",fetch = FetchType.LAZY)
    private List<Like> dislikes;

    public Post(Long id,String title, String text,User user) {
        this.id=id;
        this.title = title;
        this.text = text;
        this.user=user;
    }
}