package com.casino.rest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casino.rest.dto.EventoDto;
import com.casino.rest.dto.converter.EventoDtoConverter;
import com.casino.rest.modelo.JugadorEntity;
import com.casino.rest.modelo.PartidaEntity;
import com.casino.rest.repos.EventoRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor 
@AllArgsConstructor 
public class EventoService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EventoService.class);
	
	@Autowired
	EventoRepository eventoRepository;
	
	@Autowired
	private EventoDtoConverter converter;
	
	public void save(EventoDto evento) {		
		LOGGER.info(evento.toString());
		eventoRepository.save(converter.convertEventoDtoToEventoEntity(evento));
	}
	
	public void saveEvento(JugadorEntity jugador, String nomEvento) {
		
		this.save(EventoDto.builder()
			.nombre(nomEvento)
			.idJugador(jugador.getId())
			.apuesta(0.0)
			.token(jugador.getToken())
			.build());
	}
	
	public void saveEnvento(PartidaEntity partida, String nomEvento) {
		
		this.save(EventoDto.builder()
			.nombre(nomEvento)
			.idPartida(partida.getId())
			.idJugador(partida.getJugadorEntity().getId())
			.nombreJuego(partida.getJuegoEntity().getNombre())
			.token(partida.getJugadorEntity().getToken())
			.build());
	}
}
