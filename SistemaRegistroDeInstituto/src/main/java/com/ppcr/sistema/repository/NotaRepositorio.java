package com.ppcr.sistema.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ppcr.sistema.model.Nota;


@Repository
public interface NotaRepositorio extends JpaRepository <Nota, Integer>{
	
	@Transactional
	@Modifying
	@Query (value="call `sp_ingresar_notas`(:idalumno,:idcurso,:nota1,:nota2,:nota3,:nota4,:ExParcial,:ExFinal,:ExSustitutorio)", nativeQuery=true)
	void sp_ingresar_notas(
			@Param("idalumno")Integer idalumno_,
			@Param("idcurso") Integer idcurso_,
			@Param("nota1") Integer nota1_,
			@Param("nota2")Integer nota2_,
			@Param("nota3") Integer nota3_,
			@Param("nota4") Integer nota4_,
			@Param("ExParcial")Integer ExParcial_,
			@Param("ExFinal") Integer ExFinal_,
			@Param("ExSustitutorio") Integer ExSustitutorio_);
	
	@Query (value=" select * from notas where idalumno=?1 ORDER BY ?#{#pageable}",
			countQuery = " select * from notas where idalumno=?1" , nativeQuery=true)
	Page<Nota> spGetbyUser(Integer user, Pageable pageable);
	
	@Query (value="select a.* , n.*  from detalleprofesorcursos dp\r\n"
			+ "      inner join matricula m on dp.iddet = m.iddet\r\n"
			+ "      inner join alumno a on m.idalumno = a.idalumno\r\n"
			+ "      inner join notas n on m.idalumno = n.idalumno  where dp.iddet = ?1 ORDER BY ?#{#pageable}",
			countQuery = " select a.* , n.* from detalleprofesorcursos dp\r\n"
					+ "      inner join matricula m on dp.iddet = m.iddet\r\n"
					+ "      inner join alumno a on m.idalumno = a.idalumno\r\n"
					+ "      inner join notas n on m.idalumno = n.idalumno  where dp.iddet = ?1" , nativeQuery=true)
	Page<Nota> spGetbyProf(Integer iddet, Pageable pageable);
	
}
	
