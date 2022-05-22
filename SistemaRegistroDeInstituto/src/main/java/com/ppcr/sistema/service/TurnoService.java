package com.ppcr.sistema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppcr.sistema.IService.ITurno;
import com.ppcr.sistema.model.Turno;
import com.ppcr.sistema.repository.TurnoRepositorio;


@Service


public class TurnoService implements ITurno{
	
	@Autowired
	private TurnoRepositorio tr;
	
	@Override
	public List<Turno> listarTodos() {
		return (List<Turno>) tr.findAll();
	}

}
