package com.casino.rest.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casino.rest.modelo.PartidaEntity;

@Repository
public interface PartidaRepository extends JpaRepository<PartidaEntity, Long>{

}
