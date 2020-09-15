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
public class EventoDto {

	private Long id;
	private String nombre;
	private Long idPartida;
	private Long idJugador;
	private LocalDateTime fechaCreacion;
	private Double apuesta;
	private String token;
	private String nombreJuego;
}
