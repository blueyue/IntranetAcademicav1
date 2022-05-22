package com.ppcr.sistema.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ppcr.sistema.IService.IProfesor;
import com.ppcr.sistema.model.Profesor;
import com.ppcr.sistema.repository.ProfesorRepositorio;


@Service
public class ProfesoresService implements IProfesor{

	@Autowired
	private ProfesorRepositorio data;
	
	
	@Override
	public Page<Profesor> GetbyApe(String prof_ape,Pageable pageable) {
		return data.spGetbyApe(prof_ape, pageable);
	}

	@Override
	public Page<Profesor> GetAll(Pageable pageable) {
		return data.findAll(pageable);
	}
	
	@Override
	public List<Profesor> BuscarTodos() {
		return (List<Profesor>)data.findAll();
	}

	@Override
	public Optional<Profesor> BuscarPorId(int id) {
		return data.findById(id);
	}

	@Override
	public Profesor BuscarPorIdentity(int id) {
		return data.getById(id);
	}

	
	@Override
	public void guardar(Profesor p) {
		 data.save(p);
	}

	@Override
	public void eliminar(int idprofesor) {
		data.deleteById(idprofesor);
	}

	
	//A pedido
		@Override
		public Profesor buscaporUser(Integer iduser, Integer rol) {
			return data.spBuscarUser(iduser, rol);
		}
}
