package com.casino.rest.modelo;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="partida")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartidaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="id_jugador", referencedColumnName="id", nullable = true)
	private JugadorEntity jugadorEntity;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="id_juego", referencedColumnName="id", nullable = true)
	private JuegoEntity juegoEntity;
	
	@Column(name="balance_partida")
	private Double balancePartida;
	
	@Column(name="fecha_creacion")
	@CreatedDate
	private LocalDateTime fechaCreacion;
	
	@Column(name="fecha_fin_partida")
	private LocalDateTime fechaFinPartida;
	
	private Double apuesta;
	
	@Column(name="resultado_jugada")
	private Integer resultadoJugada;
	
	
}
