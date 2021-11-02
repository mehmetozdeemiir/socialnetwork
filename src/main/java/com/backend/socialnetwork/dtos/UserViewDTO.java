package com.backend.socialnetwork.dtos;
import com.backend.socialnetwork.entities.User;
import lombok.Data;

@Data
public final class UserViewDTO
{
    private final Long id;
    private final String userName;
    private final String password;

    //değişkenlerin final immutable olması gerekiyor bu yüzdende constructordan gecmemiz gerekiyor. ve constructorun dışarı açılmasını istemiyoruz private olusturacağız.
    //immutable: bir kez olusturulduktan sonra değşmeyen yapılar
    //private constructor bu class ın başka yerde instance olusturulmasını engeller.
    private UserViewDTO(Long id ,String userName, String password) {
        this.id=id;
        this.userName = userName;
        this.password = password;
    }

    public static UserViewDTO of(User user){
        return new UserViewDTO(user.getId(),user.getUserName(), user.getPassword());
    }
}
