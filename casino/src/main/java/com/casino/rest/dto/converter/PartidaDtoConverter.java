package com.casino.rest.dto.converter;

import org.springframework.stereotype.Component;

import com.casino.rest.dto.PartidaDto;
import com.casino.rest.modelo.PartidaEntity;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class PartidaDtoConverter {

	public PartidaDto convertPartidaEntityToPartidaDto(PartidaEntity partidaEntity) {
		
		return PartidaDto.builder()
				.id(partidaEntity.getId())
				.juegoEntity(partidaEntity.getJuegoEntity())
				.jugadorEntity(partidaEntity.getJugadorEntity())
				.balancePartida(partidaEntity.getBalancePartida())
				.fechaCreacion(partidaEntity.getFechaCreacion())
				.fechaFinPartida(partidaEntity.getFechaFinPartida())
				.apuesta(partidaEntity.getApuesta())
				.resultadoJugada(partidaEntity.getResultadoJugada())
				.build();
	}
	
	public PartidaEntity convertPartidaDtoToPartidaEntity(PartidaDto partidaDto) {
		
		return PartidaEntity.builder()
				.id(partidaDto.getId())
				.juegoEntity(partidaDto.getJuegoEntity())
				.jugadorEntity(partidaDto.getJugadorEntity())
				.balancePartida(partidaDto.getBalancePartida())
				.fechaCreacion(partidaDto.getFechaCreacion())
				.fechaFinPartida(partidaDto.getFechaFinPartida())
				.apuesta(partidaDto.getApuesta())
				.resultadoJugada(partidaDto.getResultadoJugada())
				.build();
	}
}
