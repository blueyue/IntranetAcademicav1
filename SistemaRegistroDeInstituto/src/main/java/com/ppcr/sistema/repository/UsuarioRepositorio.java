package com.ppcr.sistema.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ppcr.sistema.model.Alumno;
import com.ppcr.sistema.model.Usuario;


@Repository
public interface UsuarioRepositorio extends JpaRepository <Usuario, Integer> {


	
	@Query (value="{call spLogin(:newuser,:newpass,:tipo)}", nativeQuery=true)
	Usuario spLogin(@Param("newuser")String usuario, @Param("newpass") String contrasena, @Param("tipo") Integer tipo);


	@Transactional
	@Modifying
	@Query (value="call `sp_nuevo_usuario`(:nomusuario_,:contrasena_,:rol_)", nativeQuery=true)
	void sp_nuevo_usuario(
			@Param("nomusuario_") String nomusuario_,
			@Param("contrasena_") String contrasena_,
			@Param("rol_")Integer rol_);
	
	@Query (value="select *  from usuario where nomusuario=?1 ORDER BY ?#{#pageable}",
			countQuery = "select *  from usuario where nomusuario=?1" , nativeQuery=true)
	Page<Usuario> spGetbyUser(String user, Pageable pageable);
	
	@Transactional
	@Modifying
	@Query (value="update usuario set estado = ?1 where idusuario = ?2" , nativeQuery=true)
	void spChangeState(Integer estado,Integer idusuario);
	
	@Transactional
	@Modifying
	@Query (value="update usuario set nomusuario = ?1 ,contrasena = ?2 where idusuario = ?3" , nativeQuery=true)
	void guardareditbyalum(String string, String string2, Integer integer);
}

