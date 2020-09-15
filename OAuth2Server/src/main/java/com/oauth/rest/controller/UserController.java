package com.oauth.rest.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oauth.rest.dto.CreateUserDto;
import com.oauth.rest.dto.GetUserDto;
import com.oauth.rest.dto.UserDtoConverter;
import com.oauth.rest.model.UserEntity;
import com.oauth.rest.service.UserEntityService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController { 
	
	private final UserEntityService userEntityService;
	private final UserDtoConverter userDtoConverter;
	
	
	@PostMapping("/")
	public GetUserDto nuevoUsuario(@RequestBody CreateUserDto newUser) {
			return userDtoConverter.convertUserEntityToGetUserDto(userEntityService.nuevoUsuario(newUser));

	}
	
	@GetMapping("/me")
	public GetUserDto me(@AuthenticationPrincipal UserEntity theUser) {
		return userDtoConverter.convertUserEntityToGetUserDto(theUser);
	}

}
