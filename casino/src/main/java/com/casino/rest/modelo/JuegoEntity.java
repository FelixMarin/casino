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
@Table(name="juego")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JuegoEntity {

	@Column(name="id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	private String tipo;
	
	private String premio;
	
	@Column(name="apuesta_maxima")
	private Double apuestaMaxima;
	
	@Column(name="apuesta_minima")
	private Double apuestaMinima;
	
	@Column(name="tiempo_permitido")
	private Integer tiempoPermitido; 
	
	@Column(name="probabilidad_acierto")
	private Double probabilidadAcierto;
	
	@Column(name="fecha_creacion")
	@CreatedDate
	private LocalDateTime fechaCreacion;
}
