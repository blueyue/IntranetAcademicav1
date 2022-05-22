package com.ppcr.sistema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ppcr.sistema.IService.IAlumno;
import com.ppcr.sistema.model.Alumno;
import com.ppcr.sistema.repository.AlumnoRepositorio;


@Service
public class AlumnoService implements IAlumno{
	
	@Autowired
	private AlumnoRepositorio alumnoRepositorio;
	
	@Override
	public List<Alumno> listarTodos() {
		return (List<Alumno>) alumnoRepositorio.findAll();
	}

	@Override
	public void guardar(Alumno alumnos) {
		alumnoRepositorio.save(alumnos);
	}

	@Override
	public Alumno buscarPorId(Long idalumno) {
		return alumnoRepositorio.findById(idalumno).orElse(null);
	}

	@Override
	public void eliminar(Long idalumno) {
		alumnoRepositorio.deleteById(idalumno);
	}
	
	//A pedido
	@Override
	public Alumno buscaporUser(Integer iduser, Integer rol) {
		return alumnoRepositorio.spBuscarUser(iduser, rol);
	}
	

	
	// A pedido
		@Override
		public Page<Alumno> GetbyApe(Pageable pageable, String alum_ape) {
			return (Page<Alumno>) alumnoRepositorio.spGetbyApe(alum_ape, pageable);
		}
		
		@Override
		public Page<Alumno> GetAll(Pageable pageable) {
			return (Page<Alumno>) alumnoRepositorio.spGetAll(pageable);
		}
		
		
		@Override
		public Page<Alumno> GetAllGnl(Pageable pageable) {
			return (Page<Alumno>) alumnoRepositorio.findAll(pageable);
		}
		
		
		@Override
		public Page<Alumno> GetbyApeGnl(Pageable pageable, String alum_ape) {
			return (Page<Alumno>) alumnoRepositorio.spGetbyApeGnl(alum_ape, pageable);
		}
		

}
