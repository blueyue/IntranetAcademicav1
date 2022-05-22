package com.ppcr.sistema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ppcr.sistema.IService.IBoleta;
import com.ppcr.sistema.model.Boleta;
import com.ppcr.sistema.repository.BoletaRepositorio;



@Service
public class BoletaService implements IBoleta {

	@Autowired
	private BoletaRepositorio br;
	

	@Override
	public Page<Boleta> getAll(Pageable pageable) {
		return br.getAllDesc(pageable);
	}
	
	@Override
	public Page<Boleta> getAllCod(String codalumn,Pageable pageable) {
		return br.getAllCod(codalumn, pageable);
	}
	
	@Override
	public Page<Boleta> getAllCodEstado(String codalumn1, Integer estado2, Pageable pageable) {
		return br.getAllCodEstado(codalumn1, estado2, pageable);
	}

	@Override 
	public void ConfirmacionPagos(Integer idboleta) {
		br.spConfirmacionPagoMatricula(idboleta);
	}
}
