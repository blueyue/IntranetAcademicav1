package com.ppcr.sistema.IService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ppcr.sistema.model.Boleta;


public interface IBoleta {


	Page<Boleta> getAll(Pageable pageable);

	Page<Boleta> getAllCod(String codalumn, Pageable pageable);

	Page<Boleta> getAllCodEstado(String codalumn1, Integer estado2, Pageable pageable);

	void ConfirmacionPagos(Integer idboleta);
}
