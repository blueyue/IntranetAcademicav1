package com.ppcr.sistema.IService;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ppcr.sistema.model.DetalleCurso;


public interface IDetalleCurso {

	public List<DetalleCurso> listarTodosDetC();
	public void guardarDetC (DetalleCurso cursoprofesor);
	
	public DetalleCurso buscarPorIdDetC(Long iddet);
	public void eliminarDetC(Long iddet);
	
	
	  Page<DetalleCurso> getAllDetC(Pageable pageable);
	Page<DetalleCurso> buscarPorProf(Integer idprof, Pageable pageable);
	Page<DetalleCurso> buscarMenuProf(Integer idprof, Pageable pageable);
}
