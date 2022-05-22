package com.ppcr.sistema.IService;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ppcr.sistema.model.Usuario;


public interface IUsuario {
	
	public Usuario login (String usuario, String contrasena,Integer tipo);
	
	Page<Usuario> listarTodos(Pageable pageable);

	public void guardar(Usuario usuarios);

	public void eliminar(Integer idusuario);

	Page<Usuario> GetbyUser(String user, Pageable pageable);

	void ChangeStated(Integer id,Integer est);

	Optional<Usuario> buscarPorId(Integer idusuario);

	void guardareditbyalum(Usuario usuarios);

	
}
