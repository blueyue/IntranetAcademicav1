package com.ppcr.sistema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.ppcr.sistema.model.Carrera;



@Repository
public interface CarreraRepositorio extends JpaRepository <Carrera, Integer>{

	@Query (value="select * from carrera where nomcarrera=?1 ORDER BY ?#{#pageable}",
			countQuery = "select * from carrera where nomcarrera=?1 " , nativeQuery=true)
	Page<Carrera> spGetbyNomCar(String nomcarrera, Pageable pageable);
	
}
