package com.backend.socialnetwork.security;

import com.backend.socialnetwork.entities.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//UserDetails Spring Securitynin sağladığı bir arayüz. Spring Security sadece userName ve password olan ektra bir class yaratmamızı istiyor bizden authenticatonda kullanmak için UserDetails adında bir sınıfı implement ediyoruz.
@Getter
@Setter
public class JwtUserDetails implements UserDetails {

    public Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    private JwtUserDetails(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }
    //User entity i userdetails e ceviren metod . Authentication icin kullanacağımız userı donuyoruz.
    public static JwtUserDetails create(User user){
        List<GrantedAuthority> authorityList =new ArrayList<>(); //?
        authorityList.add(new SimpleGrantedAuthority("user"));//?
        return new JwtUserDetails(user.getId(), user.getUserName(), user.getPassword(),authorityList);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
