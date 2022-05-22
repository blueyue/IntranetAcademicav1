package com.ppcr.sistema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ppcr.sistema.model.Administrador;

@Repository
public interface AdminRepositorio extends JpaRepository<Administrador, Integer> {
	
	@Query (value="{call spBuscarUser(:iduser,:rol_)}", nativeQuery=true)
	Administrador spBuscarUser(@Param("iduser")Integer idusuarioIN,@Param("rol_") Integer rolIN);


}
