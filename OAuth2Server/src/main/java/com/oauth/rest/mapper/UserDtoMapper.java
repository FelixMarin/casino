package com.oauth.rest.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.oauth.rest.dto.GetUserDto;
import com.oauth.rest.model.UserEntity;

@Component
public class UserDtoMapper {

    public GetUserDto toGetUserDto(UserEntity user) {
        GetUserDto dto = new GetUserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setFullName(user.getFullName());
        dto.setEmail(user.getEmail());
        dto.setRoles(
            user.getRoles()
                .stream()
                .map(Enum::name)
                .collect(Collectors.toSet())
        );
        return dto;
    }
}
