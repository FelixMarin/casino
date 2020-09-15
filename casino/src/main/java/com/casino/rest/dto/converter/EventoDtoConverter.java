package com.casino.rest.dto.converter;

import org.springframework.stereotype.Component;

import com.casino.rest.dto.EventoDto;
import com.casino.rest.modelo.EventoEntity;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor 
public class EventoDtoConverter {

	public EventoDto convertEventoEntityToEventoDto(EventoEntity evento) {
		
		return EventoDto.builder()
				.id(evento.getId())
				.nombre(evento.getNombre())
				.idPartida(evento.getIdPartida())
				.idJugador(evento.getIdPartida())
				.fechaCreacion(evento.getFechaCreacion())
				.apuesta(evento.getApuesta())
				.token(evento.getToken())
				.nombreJuego(evento.getNombreJuego())
				.build();
	}
	
	
	public EventoEntity convertEventoDtoToEventoEntity(EventoDto evento) {
		
		return EventoEntity.builder()
				.id(evento.getId())
				.nombre(evento.getNombre())
				.idPartida(evento.getIdPartida())
				.idJugador(evento.getIdJugador())
				.fechaCreacion(evento.getFechaCreacion())
				.apuesta(evento.getApuesta())
				.token(evento.getToken())
				.nombreJuego(evento.getNombreJuego())
				.build();
	}
}
