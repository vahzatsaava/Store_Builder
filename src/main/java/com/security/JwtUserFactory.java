package com.security;

import com.dto.UserDto;
import com.model.Role;
import com.model.Status;
import com.security.jwt.JwtUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {
    public JwtUserFactory() {
    }

    public static JwtUser create(UserDto userDto) {
        return new JwtUser(userDto.getId(), userDto.getUserName(), userDto.getEmail(), userDto.getPassword(),
                userDto.getStatus().equals(Status.ACTIVE),
                mapTuGrantedAuthorities(userDto.getRoles()));

    }

    private static List<GrantedAuthority> mapTuGrantedAuthorities(List<Role> roles) {
        return roles.stream().map(i -> new SimpleGrantedAuthority(i.getName())).collect(Collectors.toList());
    }

}

