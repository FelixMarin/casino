package com.oauth.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor 
@Builder
public class CreateUserDto {
	
	private String username;
	private String avatar;
	private String fullname;
	private String email;
	private String password;
	private String password2;

}
