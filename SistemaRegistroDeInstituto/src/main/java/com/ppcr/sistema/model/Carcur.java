package com.ppcr.sistema.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="detallecarreracurso")
public class Carcur implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	
	public Integer idcc;
	public Integer ciclo;
	public Integer idcarrera;
	public Integer curso;
	
	@ManyToOne
	@JoinColumn(name = "idcarrera", insertable = false, updatable = false)
	private Carrera carrera;

	@ManyToOne
	@JoinColumn(name = "curso", insertable = false, updatable = false)
	private Curso ocurso;

	public Carcur() {
		super();
	}

	public Integer getIdcc() {
		return idcc;
	}

	public void setIdcc(Integer idcc) {
		this.idcc = idcc;
	}

	public Integer getCiclo() {
		return ciclo;
	}

	public void setCiclo(Integer ciclo) {
		this.ciclo = ciclo;
	}

	public Integer getIdcarrera() {
		return idcarrera;
	}

	public void setIdcarrera(Integer idcarrera) {
		this.idcarrera = idcarrera;
	}

	public Integer getCurso() {
		return curso;
	}

	public void setCurso(Integer curso) {
		this.curso = curso;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	public Curso getOcurso() {
		return ocurso;
	}

	public void setOcurso(Curso ocurso) {
		this.ocurso = ocurso;
	}

	public Carcur(Integer idcc, Integer ciclo, Integer idcarrera, Integer curso, Carrera carrera, Curso ocurso) {
		super();
		this.idcc = idcc;
		this.ciclo = ciclo;
		this.idcarrera = idcarrera;
		this.curso = curso;
		this.carrera = carrera;
		this.ocurso = ocurso;
	}

	

	
	
}
