package com.ppcr.sistema.IService;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ppcr.sistema.model.Curso;


public interface ICurso {
	public List<Curso> listarTodosCursos();
	public void guardarCursos (Curso cursos);
	public Curso buscarPorIdCursos(Long idcurso);
	public void eliminarCursos (Long idcurso);
	Page<Curso> GetbyNomCur(String nomcurso, Pageable pageable);
	Page<Curso> GetAll(Pageable pageable);
	Curso buscarPorIdC(Long idcurso);
	
}
