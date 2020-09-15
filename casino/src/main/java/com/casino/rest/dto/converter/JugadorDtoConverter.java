package com.casino.rest.dto.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.casino.rest.dto.JugadorDto;
import com.casino.rest.modelo.JugadorEntity;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class JugadorDtoConverter {

	public List<JugadorDto> convertListJugadorEntityToJugadorDto(List<JugadorEntity> listaJugadores) {
		
		List<JugadorDto> listJugadoresDto = new ArrayList<>();
		
		listaJugadores.forEach(elem -> {
			
			listJugadoresDto.add(JugadorDto.builder()
					.id(elem.getId())
					.idProveedor(elem.getIdProveedor())
					.username(elem.getUsername())
					.avatar(elem.getAvatar())
					.token(elem.getToken())
					.apuesta(elem.getApuesta())
					.fullName(elem.getFullName())
					.email(elem.getEmail())
					.totalPartidas(elem.getTotalPartidas())
					.balanceTotal(elem.getBalanceTotal())
					.totalTiempo(elem.getTotalTiempo())
					.fechaCreacion(elem.getFechaCreacion())
					.build());
		});
		
		return listJugadoresDto;
	}
	
	public JugadorDto convertJugadorEntityToJugadorDto(JugadorEntity jugador) {
		
		return JugadorDto.builder()
				.id(jugador.getId())
				.idProveedor(jugador.getIdProveedor())
				.username(jugador.getUsername())
				.avatar(jugador.getAvatar())
				.token(jugador.getToken())
				.apuesta(jugador.getApuesta())
				.fullName(jugador.getFullName())
				.email(jugador.getEmail())
				.totalPartidas(jugador.getTotalPartidas())
				.balanceTotal(jugador.getBalanceTotal())
				.totalTiempo(jugador.getTotalTiempo())
				.fechaCreacion(jugador.getFechaCreacion())
				.build();
	}
}
