package com.casino.rest.modelo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="evento")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	@Column(name="id_partida")
	private Long idPartida;
	
	@Column(name="id_jugador")
	private Long idJugador;
	
	@Column(name="fecha_creacion")
	@CreatedDate
	private LocalDateTime fechaCreacion;
	
	private Double apuesta;
	
	private String token;
	
	@Column(name="nombre_juego")
	private String nombreJuego;
	
}
