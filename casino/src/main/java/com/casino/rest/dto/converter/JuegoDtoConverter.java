package com.casino.rest.dto.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.casino.rest.dto.JuegoDto;
import com.casino.rest.modelo.JuegoEntity;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class JuegoDtoConverter {
	
	public List<JuegoDto> convertListJuegoEntityToJuegoDto(List<JuegoEntity> listaJuegos) {
		
		List<JuegoDto> listJuegosDto = new ArrayList<>();
		
		listaJuegos.forEach(elem -> {
			
			listJuegosDto.add(JuegoDto.builder()
				.id(elem.getId())
				.nombre(elem.getNombre())
				.tipo(elem.getTipo())
				.premio(elem.getPremio())
				.apuestaMaxima(elem.getApuestaMaxima())
				.apuestaMinima(elem.getApuestaMinima())
				.probabilidadAcierto(elem.getProbabilidadAcierto())
				.tiempoPermitido(elem.getTiempoPermitido())
				.fechaCreacion(elem.getFechaCreacion())
				.build());
			
		});
		
		return listJuegosDto;
	}
	
	public JuegoDto convertJuegoEntityToJuegoDto(JuegoEntity juegoEntity) {
		
		return JuegoDto.builder()
				.id(juegoEntity.getId())
				.nombre(juegoEntity.getNombre())
				.tipo(juegoEntity.getTipo())
				.premio(juegoEntity.getPremio())
				.apuestaMaxima(juegoEntity.getApuestaMaxima())
				.apuestaMinima(juegoEntity.getApuestaMinima())
				.probabilidadAcierto(juegoEntity.getProbabilidadAcierto())
				.tiempoPermitido(juegoEntity.getTiempoPermitido())
				.fechaCreacion(juegoEntity.getFechaCreacion())
				.build();
	}
}
