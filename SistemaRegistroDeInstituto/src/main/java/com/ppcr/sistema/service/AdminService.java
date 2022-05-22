package com.ppcr.sistema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppcr.sistema.IService.IAdmin;
import com.ppcr.sistema.model.Administrador;
import com.ppcr.sistema.repository.AdminRepositorio;


@Service
public class AdminService implements IAdmin{

	@Autowired
	private AdminRepositorio ar;
	
	
	//A pedido
		@Override
		public Administrador buscaporUser(Integer iduser, Integer rol) {
			return ar.spBuscarUser(iduser, rol);
		}
}
