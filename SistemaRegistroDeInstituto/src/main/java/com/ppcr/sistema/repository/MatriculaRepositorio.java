package com.ppcr.sistema.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ppcr.sistema.model.Matricula;
import com.ppcr.sistema.model.Usuario;



@Repository
public interface MatriculaRepositorio extends JpaRepository <Matricula, Integer>{

	@Query(value = "SELECT * FROM matricula WHERE idalumno = ?1  /*#pageable*/  ORDER BY estado",
			countQuery = "SELECT count(*) FROM matricula WHERE idalumno = ?1",
		    nativeQuery = true)
	Page<Matricula> findByIdalumno(Integer idalumno,Pageable pageable);
	
	@Transactional
	@Modifying
	@Query (value="call `spnuevoMatricula`(:idcarrera,:iddet,:idalumno)", nativeQuery=true)
	void spnuevo(
			@Param("idcarrera")Integer idcarrera_,
			@Param("iddet") Integer iddet_,
			@Param("idalumno") Integer idalumno_);
	
	@Query (value="select *  from matricula m inner join alumno a on m.idalumno = a.idalumno where a.codalum = ?1 ORDER BY ?#{#pageable}",
			countQuery = "select *  from matricula m inner join alumno a on m.idalumno = a.idalumno where a.codalum = ?1" , nativeQuery=true)
	Page<Matricula> GetAllbyCodAlum(String codalum, Pageable pageable);
	
	
	@Transactional
	@Modifying
	@Query (value="delete from matricula where nro_matricula = ?1 and estado = 0", nativeQuery=true)
	void DeletebyAlumno(Integer nromatricula);
}
