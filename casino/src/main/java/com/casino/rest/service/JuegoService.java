package com.casino.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casino.rest.dto.JuegoDto;
import com.casino.rest.dto.converter.JuegoDtoConverter;
import com.casino.rest.repos.JuegoRepository;

@Service
public class JuegoService {
	
	@Autowired
	JuegoRepository juegoRepository;
	
	@Autowired
	private JuegoDtoConverter converter;
	
	public List<JuegoDto> buscarJuegos() {
		return converter.convertListJuegoEntityToJuegoDto(juegoRepository.findAll());
	}
	
	public JuegoDto cargarJuego(final Long id) {
		return converter.convertJuegoEntityToJuegoDto(juegoRepository.findById(id).get());
	}
}
