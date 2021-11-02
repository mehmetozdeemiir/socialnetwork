package com.backend.socialnetwork.business.Concretes;

import com.backend.socialnetwork.business.Abstract.UserService;
import com.backend.socialnetwork.dtos.UserCreateDTO;
import com.backend.socialnetwork.dtos.UserUpdateDTO;
import com.backend.socialnetwork.dtos.UserViewDTO;
import com.backend.socialnetwork.entities.User;
import com.backend.socialnetwork.exceptions.ApiRequestException;
import com.backend.socialnetwork.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserManager implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public UserViewDTO getUserById(Long id) {
        final User user=  userRepository.findById(id).orElseThrow(() -> new ApiRequestException("bulunamadı"));
        return UserViewDTO.of(user);
        //Id den tuttu butun değerleri dto ya cevirdi ve yansıttı
    }

    @Override
    @Transactional
    public UserViewDTO createUser(UserCreateDTO userCreateDTO) {

        if (this.userRepository.findByUserNameEquals(userCreateDTO.getUserName()) != null) {
            throw new ApiRequestException("kullanılan bir kulanıcı adı girdiniz.");
        }
        final User user = userRepository.save(new User(userCreateDTO.getId(), userCreateDTO.getUserName(), userCreateDTO.getPassword()));
        return UserViewDTO.of(user);
    }


    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public List<UserViewDTO> slice(Pageable pageable) {
        return userRepository.findAll(pageable).stream().map(UserViewDTO::of).collect(Collectors.toList());
    }

    @Override
    public User getOneUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public User saveOneUser(User newUser) {
        return userRepository.save(newUser);
    }


    @Override
    @Transactional
    public UserViewDTO updateUser(Long id, UserUpdateDTO userUpdateDTO) {
        if (this.userRepository.findByUserNameEquals(userUpdateDTO.getUserName())!=null){
            throw new ApiRequestException("Kullanılan bir kullanıcı adını güncelleyemezsiniz..");
        }
        final User user= userRepository.findById(id).orElseThrow(()-> new ApiRequestException("güncellenemedi"));
        user.setId(userUpdateDTO.getId());
        user.setUserName(userUpdateDTO.getUserName());
        //user.setFirstName(userUpdateDTO.getFirstName());
        //user.setLastName(userUpdateDTO.getFirstName());
        user.setPassword(userUpdateDTO.getPassword());
        //user.setEmail(userUpdateDTO.getEmail());
        //user.setAge(userUpdateDTO.getAge());
        //user.setPhoneNumber(userUpdateDTO.getPhoneNumber());
        final User updatedUser=userRepository.save(user);
        return UserViewDTO.of(updatedUser);//DTO cevirme durumu
    }

    @Override
    public void deleteUser(Long id) {
        User user= userRepository.findById(id).orElseThrow(()->new ApiRequestException("kaldırılamadı"));
        userRepository.deleteById(user.getId());
    }

}
