package com.casino.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casino.rest.modelo.PartidaEntity;
import com.casino.rest.negocio.partida.PartidaBusiness;
import com.casino.rest.repos.PartidaRepository;

@Service
public class PartidaService {
	
	@Autowired
	PartidaRepository partidaRepository;
	
	@Autowired
	PartidaBusiness partidaBusines;
	
	@Autowired
	private EventoService eventoService;
	
	public PartidaEntity save(PartidaEntity partida) {
		
		PartidaEntity partdaRes = partidaBusines.setFechaFinPartida(partida);
		partidaRepository.save(partdaRes);
		eventoService.saveEnvento(partdaRes, "PARTIDA");
		
		return partdaRes;
	}
		
	public PartidaEntity jugada(PartidaEntity partida) {

		if(partidaBusines.isFinPartida(partida)) {
			return PartidaEntity.builder().balancePartida(-1.0).build();
		} else {
			
			int resultadoApuesta = Math.random() < partida.getJuegoEntity().getProbabilidadAcierto()?1:0;
			
			Double res = ((resultadoApuesta == 1)?Double.parseDouble(partida.getJuegoEntity().getPremio()):-partida.getApuesta());
			
			partida.setResultadoJugada(resultadoApuesta);
			partida.setBalancePartida(res + partida.getBalancePartida());
			partida.getJugadorEntity().setBalanceTotal(res + partida.getJugadorEntity().getBalanceTotal());
			
			eventoService.saveEnvento(partida, "JUGADA");
			
			return partida;
			
		}
	}
}