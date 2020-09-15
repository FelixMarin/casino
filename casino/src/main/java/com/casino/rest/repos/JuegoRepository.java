package com.casino.rest.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casino.rest.modelo.JuegoEntity;

@Repository
public interface JuegoRepository extends JpaRepository<JuegoEntity, Long>{

}
