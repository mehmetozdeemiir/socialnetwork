package com.backend.socialnetwork.dtos;
import com.backend.socialnetwork.validations.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.*;



@Data
@AllArgsConstructor
public class UserUpdateDTO {

    private Long id;

    @Size(min = 2,max=35,message = "{backend.constraints.userName.Size.message}")
    @NotNull(message = "{backend.constraints.userName.NotNull.message}")
    private String userName;

    @NotEmpty(message = "Password cannot be null")
    @ValidPassword
    private String password;

    /*@Size(min = 2,max=35,message = "{backend.constraints.firstName.Size.message}")
    @NotNull(message = "{backend.constraints.firstName.NotNull.message}")
    private String firstName;

    @Size(min = 2,max=35,message = "{backend.constraints.lastName.Size.message}")
    @NotNull(message = "Last Name cannot be null")
    private String lastName;

    @Email(message = "You have entered an e-mail that does not comply with the spelling standards.")
    private String email;

    @Min(value=16, message = "Users under the age of 16 cannot become members")
    private int age;

    @Pattern(regexp="^(05)([0-9]{2})\\s?([0-9]{3})\\s?([0-9]{2})\\s?([0-9]{2})$",message = "Phone number format error")
    @UniquePhoneNumber(message = "an existing phone number")
    private String phoneNumber;*/
}
