package com.ppcr.sistema.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ppcr.sistema.model.Alumno;
import com.ppcr.sistema.model.Usuario;

@Repository
public interface AlumnoRepositorio extends JpaRepository<Alumno, Long>  {

	@Query (value="SELECT idalumno, alum_nom,alum_ape from alumno", nativeQuery=true)
	List<Alumno> cboalumnos();
	
	@Query (value="{call spBuscarUser(:iduser,:rol)}", nativeQuery=true)
	Alumno spBuscarUser(@Param("iduser")Integer idusuarioIN,@Param("rol") Integer rolIN);
	
	@Query (value=" select *  from usuario u inner join alumno a on u.idusuario=a.idusuario where a.alum_ape = ?1 ORDER BY ?#{#pageable}",
			countQuery = "select * from usuario u inner join alumno a on u.idusuario=a.idusuario where a.alum_ape = ?1 " , nativeQuery=true)
	Page<Alumno> spGetbyApe(String alum_ape,Pageable pageable);
	
	@Query (value=" select *  from usuario u inner join alumno a on u.idusuario=a.idusuario where rol=1 ORDER BY ?#{#pageable}",
			countQuery = "select *  from usuario u inner join alumno a on u.idusuario=a.idusuario where rol=1 " , nativeQuery=true)
	Page<Alumno> spGetAll(Pageable pageable);
	
	
	@Query (value=" select *  from  alumno where alum_ape = ?1 ORDER BY ?#{#pageable}",
			countQuery = "select * from alumno where alum_ape = ?1 " , nativeQuery=true)
	Page<Alumno> spGetbyApeGnl(String alum_ape,Pageable pageable);

}
