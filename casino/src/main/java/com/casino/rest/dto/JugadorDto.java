package com.casino.rest.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JugadorDto {

	private Long id;
	private Long idProveedor;
	private String username;
	private String avatar;
	private String token;
	private Double apuesta;
	private String fullName;
	private String email;
	private Integer totalPartidas;
	private Double balanceTotal;
	private Integer totalTiempo;
	private LocalDateTime fechaCreacion;
}
