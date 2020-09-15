package com.casino.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casino.rest.dto.JugadorDto;
import com.casino.rest.modelo.JugadorEntity;
import com.casino.rest.service.JugadorService;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.NoArgsConstructor;

@RestController
@NoArgsConstructor
@RequestMapping("/jugador")
public class JugadorController {
	
	@Autowired
	private JugadorService jugadorService;
	
	@JsonView(JugadorDto.class)
	@PostMapping(value = "/token")
	public ResponseEntity<?> buscarJugador(@RequestBody JugadorEntity jugador) {		
		return  ResponseEntity.ok().body(jugadorService.buscarJugadorPorToken(jugador));
	}
	
	@JsonView(JugadorDto.class)
	@PostMapping(value = "/save")
	public ResponseEntity<?> save(@RequestBody JugadorEntity jugador) {
		return ResponseEntity.ok().body(jugadorService.save(jugador));
	}
	
	@JsonView(JugadorDto.class)
	@PostMapping(value = "/modificarsaldo")
	public ResponseEntity<?> modificarSaldo(@RequestBody JugadorEntity jugador) {
		return ResponseEntity.ok().body(jugadorService.modificarSaldo(jugador));
	}
	
	@JsonView(JugadorDto.class)
	@PostMapping(value = "/cargarsaldo")
	public ResponseEntity<?> cargarSaldo(@RequestBody JugadorEntity jugador) {
		return ResponseEntity.ok().body(jugadorService.cargarSaldo(jugador));
	}
}
