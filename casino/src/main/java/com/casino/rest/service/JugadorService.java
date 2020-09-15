package com.casino.rest.service;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.casino.rest.modelo.JugadorEntity;
import com.casino.rest.repos.JugadorRepository;

@Service
public class JugadorService {
	
	@Autowired
	JugadorRepository jugadorRepository;
	
	@Autowired
	private EventoService eventoService;
	
	public JugadorEntity save(JugadorEntity jugador) {	
		jugadorRepository.save(jugador);
		eventoService.saveEvento(jugador, "NUEVO_JUGADOR");
		return jugador;
	}
	
	public JugadorEntity buscarJugadorPorToken(JugadorEntity jugador) {
		jugador.setId(jugadorRepository.findByToken(jugador.getToken()).get(0).getId());
		return jugador;
	}
	
	@Transactional
	public JugadorEntity modificarSaldo(JugadorEntity jugador) {
		
		String balanceTotalCadena = new DecimalFormat("#.##")
				.format((jugador.getBalanceTotal() - jugador.getApuesta()));
		
		balanceTotalCadena = balanceTotalCadena.replace(",", ".");		
		double balanceTotalModificado = Double.parseDouble(balanceTotalCadena);
		
		JugadorEntity jugadorEntity = jugadorRepository.getOne(jugador.getId());		
		jugadorEntity.setBalanceTotal(balanceTotalModificado);
		jugador.setBalanceTotal(balanceTotalModificado);
		jugadorRepository.save(jugadorEntity);
		eventoService.saveEvento(jugador, "MODIFICAR_SALDO");
		
		return jugador;
	}
	
	public JugadorEntity cargarSaldo(JugadorEntity jugador) {
		
		JugadorEntity jugadorEntity = jugadorRepository.getOne(jugador.getId());		
		jugadorEntity.setBalanceTotal(jugador.getBalanceTotal());
		jugador.setBalanceTotal(jugador.getBalanceTotal());
		jugadorRepository.save(jugadorEntity);
		eventoService.saveEvento(jugador, "CARGAR_SALDO");
		
		return jugador;
	}
}
