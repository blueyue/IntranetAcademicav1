package com.ppcr.sistema.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ppcr.sistema.model.Boleta;



@Repository
public interface BoletaRepositorio extends JpaRepository <Boleta, Integer>{

	
	@Query (value=" select *  from boleta /*#pageable*/ ORDER BY idboleta desc",
			countQuery = " select *  from boleta order by idboleta desc" , nativeQuery=true)
	Page<Boleta> getAllDesc(Pageable pageable);
	
	@Query (value="  select b.* from boleta b\r\n"
			+ "      inner join matricula m on b.idboleta = m.idboleta\r\n"
			+ "      inner join alumno a on a.idalumno = m.idalumno\r\n"
			+ "      where a.codalum = ?1 ORDER BY ?#{#pageable}" ,
			countQuery = "select b.* from boleta inner join matricula m on b.idboleta = m.idboleta inner join alumno a on a.idalumno = m.idalumno where a.codalum = ?1 ORDER BY ?#{#pageable}" , nativeQuery=true)
	Page<Boleta> getAllCod(String codalumn,Pageable pageable);
	
	@Query (value="  select b.* from boleta b\r\n"
			+ "      inner join matricula m on b.idboleta = m.idboleta\r\n"
			+ "      inner join alumno a on a.idalumno = m.idalumno\r\n"
			+ "      where a.codalum = ?1 and b.estado = ?2 \r\n"
			+ "      /*#pageable*/  order by fecha desc",
			countQuery = "select b.* from boleta inner join matricula m on b.idboleta = m.idboleta inner join alumno a on a.idalumno = m.idalumno where a.codalum = ?1 and b.estado = ?2 /*#pageable*/  order by fecha desc" , nativeQuery=true)
	Page<Boleta> getAllCodEstado(String codalumn1,Integer estado2,Pageable pageable);
	
	
	@Transactional
	@Modifying
	@Query (value="call `spConfirmacionPagos`(:idboleta_)", nativeQuery=true)
	void spConfirmacionPagoMatricula(
			@Param("idboleta_")Integer idboleta_);
}
