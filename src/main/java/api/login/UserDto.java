package api.login;

import api.config.security.UserType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class UserDto {
    private Long userId;
    private String email;
    private String username;
    private String password;
    private String name;
    private String surname;

    private UserType type;



}
