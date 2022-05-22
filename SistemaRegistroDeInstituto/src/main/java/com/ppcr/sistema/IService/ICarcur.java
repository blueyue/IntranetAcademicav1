package com.ppcr.sistema.IService;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ppcr.sistema.model.Carcur;



public interface ICarcur {


	Page<Carcur> getAll(Pageable pageable);
	public void guardar (Carcur carcur);
	public Carcur buscarPorId(Integer idcc);
	public void eliminar (Integer idcc);
	
}
