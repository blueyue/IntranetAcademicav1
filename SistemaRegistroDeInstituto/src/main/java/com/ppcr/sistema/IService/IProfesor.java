package com.ppcr.sistema.IService;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ppcr.sistema.model.Profesor;


public interface IProfesor {
	public List<Profesor>BuscarTodos();
	public Optional<Profesor>BuscarPorId(int id);

	public void eliminar (int idprofesor);
	
	public  Profesor BuscarPorIdentity(int id);
	public Profesor buscaporUser(Integer iduser, Integer rol);
	Page<Profesor> GetbyApe(String prof_ape, Pageable pageable);
	Page<Profesor> GetAll(Pageable pageable);
	void guardar(Profesor p);

}
