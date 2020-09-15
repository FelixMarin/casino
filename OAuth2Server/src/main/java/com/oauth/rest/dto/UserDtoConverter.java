package com.oauth.rest.dto;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.oauth.rest.model.UserEntity;
import com.oauth.rest.model.UserRole;

@Component
public class UserDtoConverter {
	
	public GetUserDto convertUserEntityToGetUserDto(UserEntity user) {
		return GetUserDto.builder()
				.id(user.getId())
				.username(user.getUsername())
				.avatar(user.getAvatar())
				.fullName(user.getFullName())
				.email(user.getEmail())
				.proveedor(user.getProveedor())
				.saldo(user.getSaldo())
				.roles(user.getRoles().stream()
							.map(UserRole::name)
							.collect(Collectors.toSet())
				).build();
	}

}
