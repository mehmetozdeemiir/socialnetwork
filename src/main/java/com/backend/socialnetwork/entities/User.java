package com.backend.socialnetwork.entities;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users") //Table isimleri çoğul olamaz.
@Data //@Data sıkıntı mı? getter setter yeterli mi?
@NoArgsConstructor
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@Column yok db field ismine göre db ismini ayarlıyor.
    private String userName;
    private String password;

    public User(Long id,String userName,String password) {
        this.id=id;
        this.userName = userName;
        this.password = password;
    }
    //Lazy Eager fark? db den veri cekerken farkları nedir? Hangi durumlarda lazy loading veya eager loading kullanmalıyım?.
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<Comment> comments;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<Post> posts;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<Like> likes;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<Dislike> dislikes;

}