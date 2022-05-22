package com.ppcr.sistema.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ppcr.sistema.IService.ICarcur;
import com.ppcr.sistema.model.Carcur;
import com.ppcr.sistema.repository.CarcurRepositorio;

@Service
public class CarcurService implements ICarcur {

	@Autowired
	private CarcurRepositorio carcurRepositorio;

	@Override
	public Page<Carcur> getAll(Pageable pageable) {
		return carcurRepositorio.findAll(pageable);
	}


	@Override
	public void guardar(Carcur carcur) {
		carcurRepositorio.save(carcur);
	}

	@Override
	public Carcur buscarPorId(Integer idcc) {
		Carcur carcur1=new Carcur();
		Carcur carcur2=carcurRepositorio.findById(idcc).orElse(carcur1) ;
		return carcur2;
	}

	@Override
	public void eliminar(Integer idcc) {
		carcurRepositorio.deleteById(idcc);
	}

	
}
