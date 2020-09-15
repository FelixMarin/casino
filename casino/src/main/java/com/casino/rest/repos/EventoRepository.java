package com.casino.rest.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casino.rest.modelo.EventoEntity;

@Repository
public interface EventoRepository extends JpaRepository<EventoEntity, Long>{

}
