package com.ppcr.sistema.IService;

import com.ppcr.sistema.model.Administrador;

public interface IAdmin {

	public Administrador buscaporUser(Integer iduser, Integer rol);

}
