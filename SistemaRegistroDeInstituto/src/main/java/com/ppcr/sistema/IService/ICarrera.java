package com.ppcr.sistema.IService;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ppcr.sistema.model.Carrera;


public interface ICarrera {
	public List<Carrera> listarTodosCarrera();
	public void guardarCarrera (Carrera carreras);
	public Carrera buscarPorIdCarrera(Integer idcarrera );
	public void eliminarCarrera (Integer idcarrera );
	public List<Carrera> getCarrera();
	Page<Carrera> GetbyNomCar(String nomcarrera, Pageable pageable);
	Page<Carrera> GetAll(Pageable pageable);
}
