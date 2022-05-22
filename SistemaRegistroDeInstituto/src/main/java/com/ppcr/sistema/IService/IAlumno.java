package com.ppcr.sistema.IService;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ppcr.sistema.model.Alumno;


public interface IAlumno {
	
	public List<Alumno> listarTodos();
	public void guardar (Alumno alumnos);
	public Alumno buscarPorId(Long idalumno );
	public void eliminar (Long idalumno );
	
	public Alumno buscaporUser(Integer iduser, Integer rol);
	
		Page<Alumno> GetAll(Pageable pageable);
		Page<Alumno> GetbyApe(Pageable pageable, String alum_ape);
		Page<Alumno> GetAllGnl(Pageable pageable);
		Page<Alumno> GetbyApeGnl(Pageable pageable, String alum_ape);

}
