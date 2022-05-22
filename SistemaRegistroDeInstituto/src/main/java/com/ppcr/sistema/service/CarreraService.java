package com.ppcr.sistema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ppcr.sistema.IService.ICarrera;
import com.ppcr.sistema.model.Carrera;
import com.ppcr.sistema.repository.CarreraRepositorio;

@Service
public class CarreraService implements ICarrera{

	@Autowired
	private CarreraRepositorio carreraRepositorio;
	
	
	@Override
	public Page<Carrera> GetbyNomCar(String nomcarrera,Pageable pageable) {
		return carreraRepositorio.spGetbyNomCar(nomcarrera, pageable);
	}
	
	@Override
	public Page<Carrera> GetAll(Pageable pageable) {
		return carreraRepositorio.findAll(pageable);
	}
	
	@Override
	public List<Carrera> listarTodosCarrera() {
		return (List<Carrera>) carreraRepositorio.findAll();
	}

	@Override
	public void guardarCarrera(Carrera carreras) {
		carreraRepositorio.save(carreras);
	}

	@Override
	public Carrera buscarPorIdCarrera(Integer idcarrera) {
		return carreraRepositorio.findById(idcarrera).orElse(null);
	}

	@Override
	public void eliminarCarrera(Integer idcarrera) {
		carreraRepositorio.deleteById(idcarrera);
	}
	
	@Override
	public List<Carrera> getCarrera(){
		return carreraRepositorio.findAll();
	}

}
