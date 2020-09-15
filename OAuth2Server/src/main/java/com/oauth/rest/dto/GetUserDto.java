package com.oauth.rest.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor 
@Builder
public class GetUserDto {
	
	private Long id;
	private String username;
	private String avatar;
	private String fullName;
	private String email;
	private String proveedor;
	private Double saldo;
	private Set<String> roles;

}
