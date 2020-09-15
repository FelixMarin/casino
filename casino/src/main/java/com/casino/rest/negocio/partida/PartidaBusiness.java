package com.casino.rest.negocio.partida;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.casino.rest.modelo.PartidaEntity;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class PartidaBusiness {
	
	public PartidaEntity  setFechaFinPartida(PartidaEntity partida) {
		partida.setFechaFinPartida(LocalDateTime.now()
				.plusMinutes(partida.getJuegoEntity().getTiempoPermitido()));
		return partida;
	}

	public boolean isFinPartida(PartidaEntity partida) {
		return LocalDateTime.now().isAfter(partida.getFechaFinPartida());
	}
}
