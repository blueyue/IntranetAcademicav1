package com.ppcr.sistema.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ppcr.sistema.IService.IDetalleCurso;
import com.ppcr.sistema.model.DetalleCurso;
import com.ppcr.sistema.repository.DetalleCPRepositorio;



@Service
public class DetalleCPService  implements IDetalleCurso{

	@Autowired
	private DetalleCPRepositorio dcpr;
	
	@Override
	public List<DetalleCurso> listarTodosDetC() {
		return (List<DetalleCurso>) dcpr.findAll();
	}

	
	@Override
	public void guardarDetC(DetalleCurso cursoprofesor) {		
		dcpr.save(cursoprofesor);
	}

	@Override
	public DetalleCurso buscarPorIdDetC(Long iddet) {
		DetalleCurso cursoprofesor1=new DetalleCurso();
		DetalleCurso cursoprofesor2=dcpr.findById(iddet).orElse(cursoprofesor1) ;
		return cursoprofesor2;
	}

	@Override
	public void eliminarDetC(Long iddet) {
		dcpr.deleteById(iddet);
	}

	@Override
	public Page<DetalleCurso> getAllDetC(Pageable pageable) {
		return dcpr.findAll(pageable);
	}
	
	//admin
	@Override
	public Page<DetalleCurso> buscarPorProf (Integer idprof , Pageable pageable) {
		return dcpr.findByIdProf(idprof, pageable);
	}
	//profesor
	@Override
	public Page<DetalleCurso> buscarMenuProf (Integer idprof , Pageable pageable) {
		return dcpr.spMenu(idprof, pageable);
	}

}
