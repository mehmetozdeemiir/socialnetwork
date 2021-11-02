package com.backend.socialnetwork.controllers;
import com.backend.socialnetwork.business.Abstract.UserService;
import com.backend.socialnetwork.dtos.UserCreateDTO;
import com.backend.socialnetwork.dtos.UserUpdateDTO;
import com.backend.socialnetwork.dtos.UserViewDTO;
import com.backend.socialnetwork.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users") //ResponseEntityler düzeltildi.
    public List<User> getAll(){
        return userService.getAll();
    }

    @GetMapping("/users/{id}")
    public UserViewDTO getUserById(@Valid @PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.ACCEPTED) //diğerleri defaultta ok donuyor.
    public UserViewDTO createUser(@Valid @RequestBody UserCreateDTO userCreateDTO){
        return userService.createUser(userCreateDTO);
    }

    @PutMapping("/users/{id}")
    public UserViewDTO updateUser(@PathVariable Long id,@Valid @RequestBody UserUpdateDTO userUpdateDTO) {
      return userService.updateUser(id,userUpdateDTO);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser( @PathVariable Long id){
        userService.deleteUser(id);
    }

    @GetMapping("/user/slice")
    public List<UserViewDTO> slice(Pageable pageable){
         return userService.slice(pageable);
    }
}
