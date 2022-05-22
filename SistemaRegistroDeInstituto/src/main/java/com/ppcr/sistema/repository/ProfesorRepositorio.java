package com.ppcr.sistema.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.ppcr.sistema.model.Profesor;


@Repository

public interface ProfesorRepositorio extends JpaRepository<Profesor,Integer>{
	
	@Query (value="{call spBuscarUser(:iduser,:rol)}", nativeQuery=true)
	Profesor spBuscarUser(@Param("iduser")Integer idusuarioIN,@Param("rol") Integer rolIN);



	@Query (value="select *  from profesor order by idprofesor desc limit 1",
			countQuery = " select *  from profesor order by idprofesor desc limit 1" , nativeQuery=true)
	Profesor getListMax();
	

	@Query (value="select * from profesor where prof_ape=?1 ORDER BY ?#{#pageable}",
			countQuery = "select * from profesor where prof_ape=?1" , nativeQuery=true)
	Page<Profesor> spGetbyApe(String prof_ape, Pageable pageable);
}
