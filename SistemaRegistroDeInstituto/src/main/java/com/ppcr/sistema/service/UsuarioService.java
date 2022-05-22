package com.ppcr.sistema.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ppcr.sistema.IService.IUsuario;
import com.ppcr.sistema.model.Usuario;
import com.ppcr.sistema.repository.UsuarioRepositorio;


@Service
public class UsuarioService implements IUsuario {
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	


	
	@Override
	public Usuario login (String usuario, String contrasena, Integer rol) {
		Usuario objnew = usuarioRepositorio.spLogin(usuario,contrasena,rol);
		return objnew;		
	}
	
	
	@Override
	public Page<Usuario> listarTodos(Pageable pageable) {
		return usuarioRepositorio.findAll(pageable);
	}
	
	@Override
	public Page<Usuario> GetbyUser(String user,Pageable pageable) {
		return usuarioRepositorio.spGetbyUser(user, pageable);
	}
	
	@Override
	public void ChangeStated (Integer id,Integer est) {
		usuarioRepositorio.spChangeState(est,id);
	}

	
	@Override
	public void guardar(Usuario usuarios) {
		usuarioRepositorio.save(usuarios);
	}
	
	
	@Override
	public void guardareditbyalum(Usuario usuarios) {
		usuarioRepositorio.guardareditbyalum(
				usuarios.getNomusuario(),
				usuarios.getContrasena(),
				usuarios.getIdusuario());
	}
	

	
	@Override
	public Optional<Usuario> buscarPorId(Integer idusuario) {
		
		Optional<Usuario> usuario=usuarioRepositorio.findById(idusuario) ;
		return usuario;
	}

	@Override
	public void eliminar(Integer idusuario) {
		usuarioRepositorio.deleteById(idusuario);
	}

	
}
