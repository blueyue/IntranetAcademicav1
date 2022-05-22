package com.ppcr.sistema.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ppcr.sistema.model.DetalleCurso;
import com.ppcr.sistema.model.Nota;



@Repository
public interface DetalleCPRepositorio extends JpaRepository <DetalleCurso, Long>{
	
	@Query(value = "select * from detalleProfesorCursos d \r\n"
			+ "    inner join curso c on d.idcurso=c.idcurso \r\n"
			+ "    inner join profesor p on d.idprofesor=p.idprofesor\r\n"
			+ "    inner join turno t on d.idturno = t.idturno  WHERE d.idprofesor = ?1 ORDER BY ?#{#pageable}",
			countQuery = "select count(*) from detalleProfesorCursos d \r\n"
					+ "    inner join curso c on d.idcurso=c.idcurso \r\n"
					+ "    inner join profesor p on d.idprofesor=p.idprofesor\r\n"
					+ "    inner join turno t on d.idturno = t.idturno  WHERE d.idprofesor = ?1",
		    nativeQuery = true)
	Page<DetalleCurso> findByIdProf(Integer idprof,Pageable pageable);
	
	@Query (value="select *  from detalleprofesorcursos where idprofesor=?1 and estado in (1,2) /*#pageable*/  order by estado desc",
			countQuery = "select *  from detalleprofesorcursos where idprofesor=?1 and estado in (1,2) order by estado desc" , nativeQuery=true)
	Page<DetalleCurso> spMenu(Integer idprof, Pageable pageable);

}
