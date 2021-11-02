package com.backend.socialnetwork.business.Concretes;

import com.backend.socialnetwork.entities.User;
import com.backend.socialnetwork.repositories.UserRepository;
import com.backend.socialnetwork.security.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;
    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
//user ı al userdetails olarak dön
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUserName(username);
        return JwtUserDetails.create(user);
    }
//user ı al userdetails olarak dön
    public UserDetails loadUserById(Long id){
        User user =userRepository.findById(id).get();
        return JwtUserDetails.create(user);
    }
//userdetails olusturduk ve burda servisini implement ettik
    //bu classdan sonra user ı olusturmus olduk bu user icin token olusturmak için jwttokenprovider clas ı acıyoruz.

}
