package com.ppcr.sistema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.ppcr.sistema.IService.INota;
import com.ppcr.sistema.model.Nota;
import com.ppcr.sistema.repository.NotaRepositorio;




@Service
public class NotaService implements INota{
	
	@Autowired
	private NotaRepositorio notaRepositorio;
	
	
	
	@Override
	public List<Nota> listarTodosNota() {
		return (List<Nota>) notaRepositorio.findAll();
	}

	@Override
	public Page<Nota> GetAllbyAlumn(Integer user, Pageable pageable){
		return  notaRepositorio.spGetbyUser(user, pageable);
	}
	@Override
	public Page<Nota> GetbyProf(Integer iddet, Pageable pageable){
		return  notaRepositorio.spGetbyProf(iddet, pageable);
	}
	
	@Override
	public void guardarnuevoNota(Nota nota) {
		notaRepositorio.sp_ingresar_notas(
				nota.getIdalumno(),
				nota.getIdcurso(),
				nota.getNota1(),
				nota.getNota2(),
				nota.getNota3(),
				nota.getNota4(),
				nota.getExparcial(),
				nota.getExfinal(),
				nota.getExsustitutorio());

	}
	
	@Override
	public void guardarNota(Nota notas) {
		notaRepositorio.save(notas);
	}

	@Override
	public Nota buscarPorIdNota(Integer idreporte) {
		
		Nota nota1=new Nota();
		Nota nota2=notaRepositorio.findById(idreporte).orElse(nota1) ;
		return nota2;
	}

	@Override
	public void eliminarNota(Integer idreporte) {
		notaRepositorio.deleteById(idreporte);
	}

}
