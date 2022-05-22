package com.ppcr.sistema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ppcr.sistema.model.Turno;

@Repository
public interface TurnoRepositorio  extends  JpaRepository<Turno, Integer>{

}
