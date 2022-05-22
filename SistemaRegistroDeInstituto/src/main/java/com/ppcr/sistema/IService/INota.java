package com.ppcr.sistema.IService;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ppcr.sistema.model.Nota;


public interface INota {

	
	public List<Nota> listarTodosNota();

	public void guardarNota(Nota notas);

	public Nota buscarPorIdNota(Integer idreporte);

	public void eliminarNota(Integer idreporte);

	public void guardarnuevoNota(Nota nota);

	Page<Nota> GetAllbyAlumn(Integer user, Pageable pageable);

	Page<Nota> GetbyProf(Integer iddet, Pageable pageable);

}
