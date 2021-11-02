package com.backend.socialnetwork.business.Abstract;

import com.backend.socialnetwork.dtos.UserCreateDTO;
import com.backend.socialnetwork.dtos.UserUpdateDTO;
import com.backend.socialnetwork.dtos.UserViewDTO;
import com.backend.socialnetwork.entities.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    UserViewDTO getUserById(Long id);
    UserViewDTO createUser(UserCreateDTO userCreateDTO);
    UserViewDTO updateUser(Long id, UserUpdateDTO userUpdateDTO);
    void deleteUser(Long id);
    List<User> getAll();
    List<UserViewDTO> slice(Pageable pageable);
    User getOneUserByUserName(String userName);
    User saveOneUser(User newUser);
}
