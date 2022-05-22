package com.ppcr.sistema.IService;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ppcr.sistema.model.Matricula;


public interface IMatricula {

	Page<Matricula> GetAllByIdalumno(Pageable pageable, Integer id);
	public void guardar (Matricula matricula);
	public Matricula buscarPorId(int id);
	public void eliminar (int id);
	

	public void guardarnuevo(Matricula matricula);
	Page<Matricula> GetAllbyCodAlum(Pageable pageable, String codalum);
	Page<Matricula> GetAll(Pageable pageable);
	void eliminarByalumno(int id);
	
}
