package com.ppcr.sistema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ppcr.sistema.IService.ICurso;
import com.ppcr.sistema.model.Curso;
import com.ppcr.sistema.repository.CursoRepositorio;

@Service
public class CursoService implements ICurso{
	
	@Autowired
	private CursoRepositorio cursoRepositorio;

	
	@Override
	public Page<Curso> GetbyNomCur(String nomcurso,Pageable pageable) {
		return cursoRepositorio.spGetbyNomCur(nomcurso, pageable);
	}
	
	@Override
	public Page<Curso> GetAll(Pageable pageable) {
		return cursoRepositorio.findAll(pageable);
	}
	
	@Override
	public List<Curso> listarTodosCursos() {
		return (List<Curso>) cursoRepositorio.findAll();
	}

	
	@Override
	public void guardarCursos(Curso cursos) {
		cursoRepositorio.save(cursos);
	}

	@Override
	public Curso buscarPorIdCursos(Long idcurso) {
		//return cursoRepositorio.findById(idcurso).orElse(null);
		Curso curso1=new Curso();
		Curso curso2=cursoRepositorio.findById(idcurso).orElse(curso1) ;
		return curso2;
	}

	
	@Override
	public Curso buscarPorIdC(Long idcurso) {
		return cursoRepositorio.findById(idcurso).orElse(null);
	}
	@Override
	public void eliminarCursos(Long idcurso) {
		cursoRepositorio.deleteById(idcurso);
	}	


}
