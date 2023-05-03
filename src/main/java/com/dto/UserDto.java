package com.dto;

import com.model.Role;
import com.model.Status;
import com.model.User;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String userName;
    private Status status;
    private String email;
    private String password;
    private String phone;
    private List<Role> roles;

    public static UserDto from(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setPhone(user.getPhone());
        userDto.setUserName(user.getUserName());
        userDto.setStatus(user.getStatus());
        userDto.setRoles(user.getRoles());
    return userDto;
    }

}
