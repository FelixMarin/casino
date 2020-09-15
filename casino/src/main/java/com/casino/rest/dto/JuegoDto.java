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
public class JuegoDto {

	private Long id;
	private String nombre;
	private String tipo;
	private String premio;
	private Double apuestaMaxima;
	private Double apuestaMinima;
	private Integer tiempoPermitido; 
	private Double probabilidadAcierto;
	private LocalDateTime fechaCreacion;
}
