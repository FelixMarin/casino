package com.casino.rest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casino.rest.dto.JuegoDto;
import com.casino.rest.service.JuegoService;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.NoArgsConstructor;

@RestController
@NoArgsConstructor
@RequestMapping("/juego")
public class JuegoController {
	
	@Autowired
	private JuegoService juegoService;
	
	@JsonView(JuegoDto.class)
	@GetMapping(value = "/juegos")
	public ResponseEntity<?> buscarJuegos(HttpServletRequest request) {		
		return  ResponseEntity.ok().body(juegoService.buscarJuegos());
	}
	
	@JsonView(JuegoDto.class)
	@GetMapping(value = "/juego/{id}")
	public ResponseEntity<?> cargarJuego(@PathVariable("id") Long id) {		
		return  ResponseEntity.ok().body(juegoService.cargarJuego(id));
	}
}