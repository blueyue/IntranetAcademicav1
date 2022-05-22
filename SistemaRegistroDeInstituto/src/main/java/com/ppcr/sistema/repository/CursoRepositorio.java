package com.ppcr.sistema.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ppcr.sistema.model.Curso;


@Repository
public interface CursoRepositorio extends JpaRepository <Curso, Long>{

	
	@Query (value="SELECT idcurso, nomcurso from curso", nativeQuery=true)
	List<Curso> cbocursos();
	
	
	@Query (value="select * from curso where nomcurso=?1 ORDER BY ?#{#pageable}",
			countQuery = "select * from curso where nomcurso=?1" , nativeQuery=true)
	Page<Curso> spGetbyNomCur(String nomcurso, Pageable pageable);
}
