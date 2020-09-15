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
@Table(name="jugador")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JugadorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="id_proveedor")
	private Long idProveedor;
	
	private String username;
	private String avatar;
	private String fullName;
	private String email;
	private String token;
	private Double apuesta;
	
	@Column(name="total_partidas")
	private Integer totalPartidas;
	
	@Column(name="total_balance")
	private Double balanceTotal;
	
	@Column(name="total_tiempo")
	private Integer totalTiempo;
	
	@Column(name="fecha_creacion")
	@CreatedDate
	private LocalDateTime fechaCreacion;
}
