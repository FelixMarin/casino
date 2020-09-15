package com.casino.rest.dto;

import java.time.LocalDateTime;

import com.casino.rest.modelo.JuegoEntity;
import com.casino.rest.modelo.JugadorEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PartidaDto {

	private Long id;
	private JugadorEntity jugadorEntity;
	private JuegoEntity juegoEntity;
	private Double balancePartida;
	private LocalDateTime fechaCreacion;
	private LocalDateTime fechaFinPartida;
	private Double apuesta;
	private Integer resultadoJugada;
}
