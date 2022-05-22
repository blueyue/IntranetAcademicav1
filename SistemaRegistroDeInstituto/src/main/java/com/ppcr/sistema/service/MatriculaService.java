package com.ppcr.sistema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ppcr.sistema.IService.IMatricula;

import com.ppcr.sistema.model.Matricula;

import com.ppcr.sistema.repository.MatriculaRepositorio;



@Service
public class MatriculaService implements IMatricula {

	@Autowired
	private MatriculaRepositorio mr;
	
	//Todas la matriculas de 1 alumno
	@Override
	public Page<Matricula> GetAllByIdalumno(Pageable pageable,Integer id) {
		/*return mr.findAll(pageable);*/
		return mr.findByIdalumno(id, pageable);/*id alumno traido del login*/
	}

	//Todas la matricula de todos (busqueda por cod)
	@Override
	public Page<Matricula> GetAllbyCodAlum(Pageable pageable,String codalum) {
		return mr.GetAllbyCodAlum(codalum, pageable);
	}
	//Todas la matricula de todos (general)
	@Override
	public Page<Matricula> GetAll(Pageable pageable) {
		return mr.findAll(pageable);
	}
	
	@Override
	public void guardarnuevo(Matricula matricula) {
		mr.spnuevo(
				matricula.getIdcarrera(),
				matricula.getIddet(),
				matricula.getIdalumno());

	}
	@Override
	public void guardar(Matricula matricula) {
		mr.save(matricula);

	}

	@Override
	public Matricula buscarPorId(int id) {
		return mr.findById(id).orElse(null);
	}

	@Override
	public void eliminar(int id) {
		mr.deleteById(id);
	}
	
	@Override
	public void eliminarByalumno (int id) {
		mr.DeletebyAlumno(id);
	}
	

}
