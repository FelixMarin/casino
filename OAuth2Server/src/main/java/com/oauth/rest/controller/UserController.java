package com.oauth.rest.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oauth.rest.dto.CreateUserDto;
import com.oauth.rest.dto.GetUserDto;
import com.oauth.rest.mapper.UserDtoMapper;
import com.oauth.rest.model.UserEntity;
import com.oauth.rest.service.UserEntityService;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserEntityService userEntityService;
    private final UserDtoMapper userDtoMapper;

    public UserController(UserEntityService userEntityService,
                          UserDtoMapper userDtoMapper) {
        this.userEntityService = userEntityService;
        this.userDtoMapper = userDtoMapper;
    }

    @PostMapping
    public GetUserDto nuevoUsuario(@RequestBody CreateUserDto newUser) {
        UserEntity created = userEntityService.nuevoUsuario(newUser);
        return userDtoMapper.toGetUserDto(created);
    }

    @GetMapping("/me")
    public GetUserDto me(@AuthenticationPrincipal UserEntity authenticatedUser) {
        return userDtoMapper.toGetUserDto(authenticatedUser);
    }
}
