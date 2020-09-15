package com.casino.rest.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casino.rest.modelo.JugadorEntity;

@Repository
public interface JugadorRepository extends JpaRepository<JugadorEntity, Long>{

	public abstract List<JugadorEntity> findByToken(String token);
	
}