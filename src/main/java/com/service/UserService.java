package com.service;

import com.dto.UserDto;
import com.model.User;

import java.util.List;

public interface UserService {
    UserDto createUser(User user);

    UserDto updateUser(Long id, User user);

    void deleteUser(Long id);

    List<UserDto> getUsers();

    User getUserByEmail(String name);

    User findById(Long id);


}
