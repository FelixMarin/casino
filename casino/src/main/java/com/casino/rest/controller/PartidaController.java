package com.casino.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casino.rest.dto.PartidaDto;
import com.casino.rest.modelo.PartidaEntity;
import com.casino.rest.service.PartidaService;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.NoArgsConstructor;

@RestController
@NoArgsConstructor
@RequestMapping("/partida")
public class PartidaController {
	
	@Autowired
	private PartidaService partidaService;
	
	@JsonView(PartidaDto.class)
	@PostMapping(value = "/save")
	public ResponseEntity<?> save(@RequestBody PartidaEntity partidaEntity) {
		return  ResponseEntity.ok().body(partidaService.save(partidaEntity));
	}
	
	@JsonView(PartidaDto.class)
	@PostMapping(value = "/jugada")
	public ResponseEntity<?> jugada(@RequestBody PartidaEntity partidaEntity) {
		return ResponseEntity.ok().body(partidaService.jugada(partidaEntity));
	}
}
