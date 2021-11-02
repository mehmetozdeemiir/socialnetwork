package com.backend.socialnetwork.dtos;

import com.backend.socialnetwork.validations.ValidPassword;
import lombok.Data;

@Data
public class UserRequest {
    String userName;
    @ValidPassword
    String password;
}
